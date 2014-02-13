package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import quantumcraft.core.QDERecipe;
import quantumcraft.core.QRecipeHandler;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.tile.abstracttiles.TileEnergySource;
import quantumcraft.util.TileUtil;

public class TileQDeenergizer extends TileEnergySource implements ISidedInventory {

    public int QEnergyItemBuffer = 0;
    public int lastItemValue = 0;
    public ItemStack[] inventory = new ItemStack[2];
    int processTime = -1;
    QDERecipe r;
    private boolean isProcessing = false;

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory[i];
    }

    @Override
    public int guiID() {
        return 1;
    }

    @Override
    public void onBlockBreak() {
        SimpleInventory tmp = new SimpleInventory(inventory, "tmp", 64);
        tmp.dropContents(worldObj, xCoord, yCoord, zCoord);
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        return TileUtil.decrStackSize(i, j, inventory);
    }

    private void process() {
        // we need to put in the QEnergyOutput here
        processTime = -1;
        if (inventory[1] == null) {
            inventory[1] = r.getOutputItem().copy();
        } else {
            inventory[1].stackSize++;
        }
        this.addEnergy(r.getEnergyValue());
        this.decrStackSize(0, 1);
    }

    private boolean canProcess() {

        r = QRecipeHandler.getQDERecipeFromInput(inventory[0]);
        boolean flag = true;

        if (r == null) return false;

        if (inventory[0] == null) flag = false;
        if (inventory[0] != null) {
            if (inventory[1] != null) {
                if (inventory[1].itemID != r.getOutputItem().itemID) flag = false;
            }
        }
        if (this.getMaxEnergy() - this.getCurrentEnergy() < r.getEnergyValue()) flag = false;
        return flag;

    }

    @Override public String getStatusText() {
        if (isProcessing) return EnumChatFormatting.GREEN + "Working";
        return EnumChatFormatting.YELLOW + "Idle";
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (redstonePower) return;
        if (worldObj.getWorldTime() % 20 == 0) isProcessing = canProcess();
        if (isProcessing) {
            this.lastItemValue = r.getEnergyValue();
            this.QEnergyItemBuffer = this.lastItemValue;

            if (processTime > 0) processTime--;

            this.QEnergyItemBuffer =
                    (int) (((float) processTime / (float) r.getProcessTime()) * (float) this.lastItemValue);
            if (this.processTime == 0) process();

            if (this.processTime == -1) processTime = r.getProcessTime();

        } else {
            processTime = -1;
            QEnergyItemBuffer = 0;
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
    public ItemStack getStackInSlotOnClosing(int i) {
        return null;
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
        return "Quantum De-Energizer";
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
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityplayer
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
    public void onInventoryChanged() {
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        TileUtil.readInventory(par1NBTTagCompound, this.inventory);
        this.QEnergyItemBuffer = par1NBTTagCompound.getInteger("QEnergyItemBuffer");
        this.lastItemValue = par1NBTTagCompound.getInteger("LastItemValue");
        updateNextTick = true;
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("QEnergyItemBuffer", this.QEnergyItemBuffer);
        par1NBTTagCompound.setInteger("LastItemValue", this.lastItemValue);
        TileUtil.saveInventory(par1NBTTagCompound, this.inventory);
        super.writeToNBT(par1NBTTagCompound);
    }

    @Override
    public int getMaxEnergy() {
        return 5000;
    }
}
