package quantumcraft.tile;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.*;
import quantumcraft.core.QuantumCraft;
import quantumcraft.fluid.FluidSteam;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.tile.abstracttiles.TileMachineBase;
import quantumcraft.util.TileUtil;

public class TileSteamGenerator extends TileMachineBase implements IFluidHandler, IInventory {
    private FluidTank tank = new FluidTank(12000);
    public ItemStack[] inventory = new ItemStack[1];
    private int heat = 0;

    @Override
    public int getMaxEnergy() {
        return 0;
    }

    @Override
    public int guiID() {
        return 8;
    }

    @Override
    public void onBlockBreak() {
        SimpleInventory tmp = new SimpleInventory(inventory, "tmp", 64);
        tmp.dropContents(worldObj, xCoord, yCoord, zCoord);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        tank.writeToNBT(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        nbttagcompound.setInteger("fuelBuffer", fuelBuffer);
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        nbttagcompound.setTag("Items", nbttaglist);
        super.writeToNBT(nbttagcompound);
        QuantumCraft.logHandler.debugPrint(this,
                "After write to nbt: Fuel buffer, tankFluidAmount: " + fuelBuffer + " | " + tank.getFluidAmount());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        fuelBuffer = nbttagcompound.getInteger("fuelBuffer");
        tank.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length) {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        QuantumCraft.logHandler.debugPrint(this,
                "After readFromNbt: Fuel buffer, tankFluidAmount: " + fuelBuffer + " | " + tank.getFluidAmount());
        updateNextTick = true;
    }

    @Override
    public int fill(ForgeDirection forgeDirection, FluidStack fluidStack, boolean b) {
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection forgeDirection, FluidStack resource, boolean doDrain) {
        if (resource.fluidID == FluidSteam.fluid.getID()) {
            return tank.drain(resource.amount, doDrain);
        } else {
            return null;
        }
    }

    public static int getItemBurnTime(ItemStack par0ItemStack) {
        if (par0ItemStack == null) {
            return 0;
        } else {
            int var1 = par0ItemStack.getItem().itemID;

            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null) {
                Block var3 = Block.blocksList[var1];

                if (var3 == Block.woodSingleSlab) {
                    return 150;
                }

                if (var3.blockMaterial == Material.wood) {
                    return 300;
                }
            }

            if (var1 == Item.stick.itemID) return 100;
            if (var1 == Item.coal.itemID) return 1600;
            if (var1 == Item.bucketLava.itemID) return 20000;
            if (var1 == Block.sapling.blockID) return 100;
            if (var1 == Item.blazeRod.itemID) return 2400;
            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }

    int fuelBuffer = 0;

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj.getWorldTime() % 60 == 0) {
            if (fuelBuffer >= 0) {
                if (heat < 100) heat++;
            } else {
                heat--;
            }
        }
        if (worldObj.getWorldTime() % 10 == 0) {
            QuantumCraft.logHandler.debugPrint(this, "Heat value: " + heat);
            if (getItemBurnTime(inventory[0]) > 0 && fuelBuffer <= 0 && tank.getCapacity() > tank.getFluidAmount()) {
                fuelBuffer += getItemBurnTime(inventory[0]);
                decrStackSize(0, 1);
            }
            if (fuelBuffer >= 0) {
                if (tank.getFluidAmount() >= tank.getCapacity()) return;
                QuantumCraft.logHandler.debugPrint(this, "Fluid in tank: " + tank.getFluidAmount());

                if (heat <= 0) heat = 1;
                int fuelUse = (100 / heat) * 10;
                int fluidUse = 100;
                if (fluidUse > fuelBuffer) fluidUse = fuelBuffer;
                if (fluidUse > tank.getCapacity() - tank.getFluidAmount())
                    fluidUse = tank.getCapacity() - tank.getFluidAmount();
                FluidStack fs = new FluidStack(FluidSteam.fluid, fluidUse);
                tank.fill(fs, true);
                fuelBuffer -= fuelUse;
            }
        }
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection forgeDirection, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection forgeDirection, Fluid fluid) {
        return fluid.getID() == FluidSteam.fluid.getID();
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection forgeDirection) {
        return new FluidTankInfo[]{tank.getInfo()};
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        return TileUtil.decrStackSize(i, j, inventory);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        ItemStack stack = inventory[i];
        inventory[i] = null;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        inventory[i] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityPlayer
                .getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <=
                64.0D;
    }


    @Override
    public String getInvName() {
        return "Steam Generator";
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

}
