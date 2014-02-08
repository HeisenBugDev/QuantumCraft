package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import quantumcraft.core.Loader;
import quantumcraft.core.interfaces.IUpgradable;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.tile.abstracttiles.TileEnergySource;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.TileUtil;

import java.util.Random;

public class TileQDematerializer extends TileEnergySource implements ISidedInventory, IUpgradable {

    public ItemStack[] inventory = new ItemStack[1];
    public int processTime = -1;

    Random rand = new Random();
    public int currentProcessTime = 0;

    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory[i];
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
    public String getInvName() {
        return "Quantum De-Materializer";
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
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityplayer
                .getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <=
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

    private void process() {
        processTime = -1;
        if (rand.nextInt(2) == 1) {
            this.addEnergy(rand.nextInt(5));
        }
        this.decrStackSize(0, 1);
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        return TileUtil.decrStackSize(i, j, inventory);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (redstonePower) return;
        currentProcessTime = 40 / (BasicUtils.overclockMultiplier(upgradeID) + 1);
        if (inventory[0] != null) {
            if (processTime > 0) processTime--;
            if (this.processTime == 0) process();
            if (this.processTime == -1) processTime = currentProcessTime;
        } else {
            processTime = -1;
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
    public int guiID() {
        return 4;
    }

    @Override
    public void onBlockBreak() {
        SimpleInventory tmp = new SimpleInventory(inventory, "tmp", 64);
        tmp.dropContents(worldObj, xCoord, yCoord, zCoord);
        dropUpgrades();
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        upgradeID = par1NBTTagCompound.getIntArray("Upgrades");
        TileUtil.readInventory(par1NBTTagCompound, this.inventory);
        updateNextTick = true;
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        TileUtil.saveInventory(par1NBTTagCompound, this.inventory);
        par1NBTTagCompound.setIntArray("Upgrades", upgradeID);
        super.writeToNBT(par1NBTTagCompound);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return new int[]{0};
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
    public boolean eatUpgrade(int id) {
        for (int i = 0; i < 4; i++) {
            if (upgradeID[i] == 0) {
                upgradeID[i] = id;
                return true;
            }
        }
        return false;
    }

    @Override
    public void dropUpgrades() {
        for (int i = 0; i < upgradeID.length; i++) {
            if (upgradeID[i] != 0) {
                BasicUtils.dropItem(worldObj, xCoord, yCoord, zCoord,
                        new ItemStack(Loader.ItemUpgrade, 1, upgradeID[i])); //DROP DA UPGRADE
            }
            upgradeID[i] = 0;
        }
    }
}
