package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import quantumcraft.core.Loader;
import quantumcraft.core.interfaces.IQEnergizable;
import quantumcraft.core.interfaces.IUpgradable;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.tile.abstracttiles.TileMachineBase;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.TileUtil;

public class TileQEInjector extends TileEnergySink implements ISidedInventory, IUpgradable {

    public int currentival = 0;
    public int maxival = 0;
    public ItemStack[] inventory = new ItemStack[2];

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
        return TileUtil.decrStackSize(i, j, inventory);
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

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "Quantum Energy Injector";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {

        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {

        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityplayer
                .getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <=
                64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

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
        super.updateEntity();
        if (redstonePower) return;
        if (inventory[0] == null && currentival != 0) {
            currentival = 0;
        }
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(60));
        }
        injectPower(inventory, upgradeID, true, this, this);
        if (updateNextTick) {
            // All nearby players need to be updated if the status of work
            // changes, or if heat runs out / starts up, in order to change
            // texture.
            updateNextTick = false;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    public static void injectPower(ItemStack[] inventoryLocal, int[] upgradeIDLocal, boolean runProcess,
                                   TileMachineBase tile, ISidedInventory inv) {
        if (inventoryLocal[0] != null && inventoryLocal[1] == null) {
            if (inventoryLocal[0].getItem() instanceof IQEnergizable) {
                IQEnergizable e = ((IQEnergizable) inventoryLocal[0].getItem());
                int cycle = 50 + BasicUtils.overclockMultiplier(upgradeIDLocal);
                if (e.getCurrentQEnergyBuffer(inventoryLocal[0]) <= (e.getMaxQEnergyValue() - cycle)) {
                    if (tile.getCurrentEnergy() < cycle) {
                        cycle = tile.getCurrentEnergy();
                    }
                    if (cycle != 0) {
                        e.setCurrentQEnergyBuffer(inventoryLocal[0],
                                e.getCurrentQEnergyBuffer(inventoryLocal[0]) + cycle);
                    }
                } else {
                    cycle = e.getMaxQEnergyValue() - e.getCurrentQEnergyBuffer(inventoryLocal[0]);
                    if (tile.getCurrentEnergy() < cycle) {
                        cycle = tile.getCurrentEnergy();
                    }
                    if (cycle != 0) {
                        e.setCurrentQEnergyBuffer(inventoryLocal[0],
                                e.getCurrentQEnergyBuffer(inventoryLocal[0]) + cycle);
                    }
                }
                inventoryLocal[0].getItem().setDamage(inventoryLocal[0],
                        e.getMaxQEnergyValue() - e.getCurrentQEnergyBuffer(inventoryLocal[0]));
                tile.subtractEnergy(cycle);
                tile.updateNextTick = true;

                if (e.getCurrentQEnergyBuffer(inventoryLocal[0]) == e.getMaxQEnergyValue() && runProcess) {
                    inventoryLocal[1] = inventoryLocal[0].copy();
                    inv.decrStackSize(0, 1);
                    inventoryLocal[1].getItem().setDamage(inventoryLocal[1], 1);
                } else if (e.getCurrentQEnergyBuffer(inventoryLocal[0]) == e.getMaxQEnergyValue()) {
                    inventoryLocal[0].getItem().setDamage(inventoryLocal[0], 1);
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        TileUtil.readInventory(par1NBTTagCompound, this.inventory);
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
        TileUtil.saveInventory(par1NBTTagCompound, this.inventory);
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
