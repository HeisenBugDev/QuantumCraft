package quantumcraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import quantumcraft.core.Loader;
import quantumcraft.inventory.SimpleInventory;
import quantumcraft.tile.abstracttiles.TileMachineBase;
import quantumcraft.util.TileUtil;

public class TileQDislocator extends TileMachineBase implements ISidedInventory {
    public ItemStack[] inventory = new ItemStack[2];

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return new int[]{0, 1};
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return itemstack.getItem().itemID == Loader.ItemLocationCard.itemID;
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
    public String getInvName() {
        return "Quantum Dislocator";
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

    @Override
    public int getMaxEnergy() {
        return 0;
    }

    @Override
    public int guiID() {
        return 3;
    }

    @Override
    public void onBlockBreak() {
        SimpleInventory tmp = new SimpleInventory(inventory, "tmp", 64);
        tmp.dropContents(worldObj, xCoord, yCoord, zCoord);
    }

    public boolean areCardsIn() {
        return !(inventory[0] == null || inventory[1] == null) &&
                (inventory[0].hasTagCompound() && inventory[1].hasTagCompound());
    }

    public int getCardBID(int index) {
        return worldObj.getBlockId(getx(index), gety(index), getz(index));
    }

    public int getCardBMT(int index) {
        return worldObj.getBlockMetadata(getx(index), gety(index), getz(index));
    }

    public TileEntity getCardTE(int index) {
        return worldObj.getBlockTileEntity(getx(index), gety(index), getz(index));
    }

    public void setB(int index, int id, int mt) {
        worldObj.setBlock(getx(index), gety(index), getz(index), id, mt, 3);
    }

    public int getx(int index) {
        NBTTagCompound t = inventory[index].getTagCompound().getCompoundTag("LOC");
        return t.getInteger("x");
    }

    public int gety(int index) {
        NBTTagCompound t = inventory[index].getTagCompound().getCompoundTag("LOC");
        return t.getInteger("y");
    }

    public int getz(int index) {
        NBTTagCompound t = inventory[index].getTagCompound().getCompoundTag("LOC");
        return t.getInteger("z");
    }

    public void setTE(int index, TileEntity te) {
        worldObj.setBlockTileEntity(getx(index), gety(index), getz(index), te);
    }

    private int prevAid;
    private int prevBid;
    private int prevAmt;
    private int prevBmt;
    private int prevAte;
    private int prevBte;

    private int currAte;
    private int currBte;

    boolean isFirstCycle = true;

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!shouldRun) return;
        if (areCardsIn() && worldObj != null) {
            int currAid = getCardBID(0);
            int currAmt = getCardBMT(0);
            int currBid = getCardBID(1);
            int currBmt = getCardBMT(1);
            if (getCardTE(0) != null && getCardTE(1) != null) {
                currAte = getCardTE(0).hashCode();
                currBte = getCardTE(1).hashCode();
            }
            if (isFirstCycle) {
                isFirstCycle = false;
            } else {
                if (getCardTE(0) != null && getCardTE(1) != null) {
                    if (currAte != prevAte) { //A TE has changed
                        TileEntity tte = getCardTE(0);
                        tte.xCoord = getx(1);
                        tte.yCoord = gety(1);
                        tte.zCoord = getz(1);
                        setTE(1, tte);
                    }
                    if (currBte != prevBte) { //B TE has changed
                        TileEntity tte = getCardTE(1);
                        tte.xCoord = getx(0);
                        tte.yCoord = gety(0);
                        tte.zCoord = getz(0);
                        setTE(0, tte);
                    }
                }
                if (currAid != prevAid || currAmt != prevAmt) { //A has changed
                    setB(1, currAid, currAmt);
                }
                if (currBid != prevBid || currBmt != prevBmt) { //B has changed
                    setB(0, currBid, currBmt);
                }
            }
            prevAid = currAid;
            prevAmt = currAmt;
            prevBid = currBid;
            prevBmt = currBmt;
            prevAte = currAte;
            prevBte = currBte;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length) {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        updateNextTick = true;
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
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
}
