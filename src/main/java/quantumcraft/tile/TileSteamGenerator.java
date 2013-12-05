package quantumcraft.tile;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.*;
import quantumcraft.fluid.FluidSteam;
import quantumcraft.tile.abstracttiles.TileMachineBase;
import quantumcraft.util.TileUtil;

public class TileSteamGenerator extends TileMachineBase implements IFluidHandler, IInventory {
    private FluidTank tank = new FluidTank(12000);
    private ItemStack[] inventory = new ItemStack[1];

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
        if (worldObj.getWorldTime() % 20 == 0) {
            if (getItemBurnTime(inventory[0]) > 0) {
                fuelBuffer += getItemBurnTime(inventory[0]);
                decrStackSize(0, 1);
            }
            if (fuelBuffer >= 100) {
                FluidStack fs = new FluidStack(FluidSteam.fluid, 100);
                tank.fill(fs, true);
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
        return 0;
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
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        // This may need to be like the more complicated on in TileIONForge.java
        return true;
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