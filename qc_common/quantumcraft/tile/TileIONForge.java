package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.tile.abstracttiles.TileEnergySink;

public class TileIONForge extends TileEnergySink implements ISidedInventory {

    public ItemStack[] inventory = new ItemStack[4];
    private SimpleInventory _inv = new SimpleInventory(4, "iof", 64);
    private int processTime = 0;

    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int guiID() {
        return 6;
    }

    @Override
    public void onBlockBreak() {
        //TODO Inventory
    }

    @Override
    public void updateEntity() {
        for (int i = 0; i < 2; i++) {
            if (this.canProcess(i)) {

            }
        }
    }

    private int iteratorSwitch(int i, boolean io) {
        int input = 0;
        int output = 0;
        switch (i) {
            case 0:
                input = 0;
                output = 1;
            case 1:
                input = 2;
                output = 3;
        }
        return io ? output : input;
    }

    private void process() {
        processTime = -1;

    }

    /**
     * @param i Which iteration of slots.
     *          Example: For slots 0 and 1 where 0 is
     *          input you pass it 1 and for 2 and 3 you pass it 2
     * @return Is it able to process or not.
     */
    private boolean canProcess(int i) {
        boolean process = true;
        int input = iteratorSwitch(i, false);
        int output = iteratorSwitch(i, true);

        ItemStack r = FurnaceRecipes.smelting().getSmeltingResult(inventory[input]);
        if (r == null) return false;

        if (inventory[input] == null) {
            process = false;
        }
        if (inventory[input] != null) {
            if (inventory[output] != null) {
                if (inventory[output] != r) {
                    process = false;
                }
            }
        }
        return process;
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
        return null;
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

    @Override
    public String getInvName() {
        return "ION Forge";
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
}
