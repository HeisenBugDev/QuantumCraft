package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import quantumcraft.core.interfaces.IQEnergizable;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.tile.abstracttiles.TileEnergySource;

public class TileQEExtractor extends TileEnergySource implements
        ISidedInventory {
    public int currentival = 0;
    public int maxival = 0;
    public ItemStack[] inventory = new ItemStack[2];
    private SimpleInventory _inv = new SimpleInventory(2, "qei", 64);

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
        _inv.decrStackSize(i,j);
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

        _inv.setInventorySlotContents(i, itemstack);

    }

    public void process() {
        inventory[1] = inventory[0].copy();
        decrStackSize(0, 1);
        this.currentival = 0;
    }

    @Override
    public String getInvName() {
        return "Quantum Energy Extractor";
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
    public boolean isUsableByPlayer(EntityPlayer entityplayer) {

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
        return 5;
    }

    @Override
    public void onBlockBreak() {
        _inv.dropContents(worldObj, xCoord, yCoord, zCoord);
    }

    //I think this method would like a refactor, but meh. if you have the nerves to do it, go ahead. AND DO NOT BREAK IT
    @Override
    public void updateEntity() {
        if (inventory[0] == null && currentival != 0) {
            currentival = 0;
        }
        if (inventory[0] != null && inventory[1] == null) {
            if (inventory[0].getItem() instanceof IQEnergizable) {
                IQEnergizable e = ((IQEnergizable) inventory[0].getItem());
                int cycle = 5;

                if (!(this.getCurrentEnergy() + cycle <= this.getMaxEnergy())) {
                    cycle = this.getMaxEnergy() - this.getCurrentEnergy();
                }
                if (e.getCurrentQEnergyBuffer(inventory[0]) < cycle) {
                    cycle = e.getCurrentQEnergyBuffer(inventory[0]);
                }
                e.setCurrentQEnergyBuffer(inventory[0], e.getCurrentQEnergyBuffer(inventory[0]) - cycle);
                inventory[0].getItem().setDamage(inventory[0],
                        e.getMaxQEnergyValue(inventory[0]) - e.getCurrentQEnergyBuffer(inventory[0]));
                this.addEnergy(cycle);

                this.maxival = e.getMaxQEnergyValue(inventory[0]);
                this.currentival = e.getCurrentQEnergyBuffer(inventory[0]);
                if (e.getCurrentQEnergyBuffer(inventory[0]) == 0) {
                    process();
                }
            }
        }
        if (updateNextTick) {
            // All nearby players need to be updated if the status of work
            // changes, or if heat runs out / starts up, in order to change
            // texture.
            updateNextTick = false;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);
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

}
