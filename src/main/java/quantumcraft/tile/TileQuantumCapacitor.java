package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.CapacitorName;
import quantumcraft.util.Coords;
import quantumcraft.util.TileUtil;

public class TileQuantumCapacitor extends TileEnergySink implements ISidedInventory {
    public ItemStack[] inventory = new ItemStack[2];
    public int upgradeID[] = {0, 0, 0, 0};
    private int maxEnergyMultiplier = 1;

    public void setMaxEnergyMultiplier(int max) {
        maxEnergyMultiplier = max;
    }

    @Override
    public int getMaxEnergy() {
        return 50000 * (int) Math.pow(4.0D, (double) maxEnergyMultiplier);
    }

    @Override
    public int guiID() {
        return 7;
    }

    @Override public String getStatusText() {
        return "N/A";
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        TileUtil.readInventory(par1NBTTagCompound, this.inventory);
        maxEnergyMultiplier = par1NBTTagCompound.getInteger("energyMultiplier");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        TileUtil.saveInventory(par1NBTTagCompound, this.inventory);
        par1NBTTagCompound.setInteger("energyMultiplier", maxEnergyMultiplier);
    }

    @Override
    public void onBlockBreak() {
        SimpleInventory tmp = new SimpleInventory(inventory, "tmp", 1);
        tmp.dropContents(worldObj, xCoord, yCoord, zCoord);
    }

    public int requestQuantumEnergy(Coords l, int request) {
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
        if (redstonePower) return;
        this.addEnergy(this.requestPacket(10000));
        TileQuantumEnergyInjector.injectPower(inventory, upgradeID, false, this, this);
        TileQuantumEnergyExtractor.extractPower(inventory, this, this, false, 1);
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

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "Quantum " + CapacitorName.getName(maxEnergyMultiplier) + " Capacitor";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

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
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return false;
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
