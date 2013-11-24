package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import quantumcraft.core.Loader;
import quantumcraft.core.interfaces.IQEnergizable;
import quantumcraft.core.interfaces.IUpgradable;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.tile.abstracttiles.TileMachineBase;
import quantumcraft.util.BasicUtils;

public class TileQEInjector extends TileEnergySink implements
        ISidedInventory, IUpgradable {

    public int currentival = 0;
    public int maxival = 0;
    public ItemStack[] inventory = new ItemStack[2];
    public int upgradeID[] = {0, 0, 0, 0};

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        if (var1 == 0) {
            return new int[]{1};
        } else return new int[]{0};
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return j != 0;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return i != 0;
    }

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (this.inventory[i] != null) {
            ItemStack itemstack;

            if (this.inventory[i].stackSize <= j) {
                itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            } else {
                itemstack = this.inventory[i].splitStack(j);

                if (this.inventory[i].stackSize == 0) {
                    this.inventory[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.inventory[i] != null) {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.inventory[i] = itemstack;

        if (itemstack != null
                && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {
        return "Quantum Energy Injector";
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {

        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {

        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
                this.zCoord) == this && entityplayer.getDistanceSq(
                (double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
                (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    @Override
    public int guiID() {
        return 2;
    }

    public void dropUpgrades() {
        for (int u : upgradeID) {
            if (u != 0) {
                BasicUtils.dropItem(worldObj, xCoord, yCoord, zCoord,
                        new ItemStack(Loader.ItemUpgrade, 1, u)); //DROP DA UPGRADE
                u = 0;
            }
        }

    }

    @Override
    public void onBlockBreak() {
        SimpleInventory tmp = new SimpleInventory(inventory, "tmp", 1);
        tmp.dropContents(worldObj, xCoord, yCoord, zCoord);
        dropUpgrades();
    }

    //I think this method would like a refactor, but meh. if you have the nerves to do it, go ahead. AND DO NOT BREAK IT
    @Override
    public void updateEntity() {
        if (inventory[0] == null && currentival != 0) {
            currentival = 0;
        }
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(10));
        }
        injectPower(inventory, upgradeID, this.getCurrentEnergy(), true, this, this);
        if (updateNextTick) {
            // All nearby players need to be updated if the status of work
            // changes, or if heat runs out / starts up, in order to change
            // texture.
            updateNextTick = false;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);
        }
    }

    public static void injectPower(ItemStack[] inventoryLocal, int[] upgradeIDLocal, int currentEnergy, boolean runProcess, TileMachineBase tile, ISidedInventory inv ) {
        if (inventoryLocal[0] != null && inventoryLocal[1] == null) {
            if (inventoryLocal[0].getItem() instanceof IQEnergizable) {
                IQEnergizable e = ((IQEnergizable) inventoryLocal[0].getItem());
                int cycle = 5 + BasicUtils.overclockMultiplier(upgradeIDLocal);
                if (e.getCurrentQEnergyBuffer(inventoryLocal[0]) <= (e.getMaxQEnergyValue(inventoryLocal[0]) - cycle)) {
                    if (currentEnergy < cycle) {
                        cycle = currentEnergy;
                    }
                    if (cycle != 0) {
                        e.setCurrentQEnergyBuffer(
                                inventoryLocal[0], e.getCurrentQEnergyBuffer(inventoryLocal[0]) + cycle);
                    }
                } else {
                    cycle = e.getMaxQEnergyValue(inventoryLocal[0]) - e.getCurrentQEnergyBuffer(inventoryLocal[0]);
                    if (currentEnergy < cycle) {
                        cycle = currentEnergy;
                    }
                    if (cycle != 0) {
                        e.setCurrentQEnergyBuffer(
                                inventoryLocal[0], e.getCurrentQEnergyBuffer(inventoryLocal[0]) + cycle);
                    }
                }
                inventoryLocal[0].getItem().setDamage(inventoryLocal[0],
                        e.getMaxQEnergyValue(inventoryLocal[0]) - e.getCurrentQEnergyBuffer(inventoryLocal[0]));
                tile.subtractEnergy(cycle);

                if (e.getCurrentQEnergyBuffer(inventoryLocal[0]) == e.getMaxQEnergyValue(inventoryLocal[0]) && runProcess) {
                    inventoryLocal[1] = inventoryLocal[0].copy();
                    inv.decrStackSize(0, 1);
                    inventoryLocal[1].getItem().setDamage(inventoryLocal[1], 1);
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
                    .tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length) {
                this.inventory[b0] = ItemStack
                        .loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.currentival = par1NBTTagCompound.getInteger("currentival");
        this.maxival = par1NBTTagCompound.getInteger("maxival");
        updateNextTick = true;
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("currentival", this.currentival);
        par1NBTTagCompound.setInteger("maxival", this.maxival);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
        super.writeToNBT(par1NBTTagCompound);
    }

    @Override
    public int getMaxEnergy() {
        return 100; //this is supposed to be a very small buffer
    }

    @Override
    public boolean eatUpgrade(int id) {
        for (int i = 0; i < 4; i++) {
            if (upgradeID[i] == 0) {
                upgradeID[i] = id;
                return true;
            }
        }
        return false;
    }
}
