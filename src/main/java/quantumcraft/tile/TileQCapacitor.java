package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.net.Location;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.DebugHandler;

public class TileQCapacitor extends TileEnergySink implements ISidedInventory {
    public ItemStack[] inventory = new ItemStack[2];
    public int upgradeID[] = {0, 0, 0, 0};

    @Override
    public int getMaxEnergy() {
        return 100000;
    }

    @Override
    public int guiID() {
        return 7;
    }

    @Override
    public void onBlockBreak() {
        SimpleInventory tmp = new SimpleInventory(inventory, "tmp", 1);
        tmp.dropContents(worldObj, xCoord, yCoord, zCoord);
    }

    public int requestQuantumEnergy(Location l, int request) {
        if (getCurrentEnergy() >= request) {
            subtractEnergy(request);
            updateNextTick = true;
            return request;
        } else {
            int e = getCurrentEnergy();
            subtractEnergy(request);
            updateNextTick = true;
            return e;
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        // [review] - Why do we need all these?
        if ((this.getMaxEnergy() - this.getCurrentEnergy()) >= 100) {
            this.addEnergy(this.requestPacket(100));
        } else if ((this.getMaxEnergy() - this.getCurrentEnergy()) >= 10) {
            this.addEnergy(this.requestPacket(10));
        } else if ((this.getMaxEnergy() - this.getCurrentEnergy()) >= 1) {
            this.addEnergy(this.requestPacket(1));
        }
        TileQEInjector.injectPower(inventory, upgradeID, false, this, this);
        TileQEExtractor.extractPower(inventory, this, this, false, 1);
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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

        if (itemstack != null &&
                itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {
        return "Quantum Capacitor";
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj
                .getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) ==
                this && entityplayer.getDistanceSq((double) this.xCoord + 0.5D,
                (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <=
                64.0D;
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
    public int[] getAccessibleSlotsFromSide(int var1) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return false;
    }
}
