package mods.quantumcraft.machine;

import mods.quantumcraft.core.Loader;
import mods.quantumcraft.core.network.PacketHandler;
import mods.quantumcraft.core.network.packets.QDislocatorInitPacket;
import mods.quantumcraft.inventory.SimpleInventory;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileQDislocator extends TileMachineBase implements ISidedInventory {
    public ItemStack[] inventory = new ItemStack[2];
    private SimpleInventory _inv = new SimpleInventory(2, "qei", 64);

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return new int[] {0,1};
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        if (itemstack.getItem().itemID == Loader.ItemLocationCard.itemID) return true;
        return false;
    }

    @Override
    public boolean canRotate() {
        return true;
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

        _inv.setInventorySlotContents(i, itemstack);

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
    public void onBlockBreak() {
        _inv.dropContents(worldObj, xCoord, yCoord, zCoord);
    }

    public boolean areCardsIn() {
        if (inventory[0] == null || inventory[1] == null) return false;
        return (inventory[0].hasTagCompound() && inventory[1].hasTagCompound());
    }

    public int getCardBID(int index) {
        NBTTagCompound t = inventory[index].getTagCompound().getCompoundTag("LOC");
        int x = t.getInteger("x");
        int y = t.getInteger("y");
        int z = t.getInteger("z");

        return worldObj.getBlockId(x,y,z);
    }

    public int getCardBMT(int index) {
        NBTTagCompound t = inventory[index].getTagCompound().getCompoundTag("LOC");
        int x = t.getInteger("x");
        int y = t.getInteger("y");
        int z = t.getInteger("z");

        return worldObj.getBlockMetadata(x,y,z);
    }

    public TileEntity getCardTE(int index) {
        NBTTagCompound t = inventory[index].getTagCompound().getCompoundTag("LOC");
        int x = t.getInteger("x");
        int y = t.getInteger("y");
        int z = t.getInteger("z");

        return worldObj.getBlockTileEntity(x,y,z);
    }

    public void setB(int index, int id, int mt) {
        NBTTagCompound t = inventory[index].getTagCompound().getCompoundTag("LOC");
        int x = t.getInteger("x");
        int y = t.getInteger("y");
        int z = t.getInteger("z");

        worldObj.setBlock(x,y,z,id,mt,3);
    }

    public void setTE(int index, TileEntity te) {
        NBTTagCompound t = inventory[index].getTagCompound().getCompoundTag("LOC");
        int x = t.getInteger("x");
        int y = t.getInteger("y");
        int z = t.getInteger("z");

        worldObj.setBlockTileEntity(x,y,z,te);
    }

    private int prevAid;
    private int prevBid;
    private int prevAmt;
    private int prevBmt;

    private int currAid;
    private int currBid;
    private int currAmt;
    private int currBmt;

    boolean isFirstCycle = true;

    @Override
    public void updateEntity() {
        if (areCardsIn() && worldObj != null) {
            currAid = getCardBID(0);
            currAmt = getCardBMT(0);
            currBid = getCardBID(1);
            currBmt = getCardBMT(1);
            if (isFirstCycle) {
                isFirstCycle = false;
            }
            else
            {
                if (getCardTE(0) != null && getCardTE(1) != null) {
                    if (!getCardTE(0).equals(getCardTE(1))) {
                        setTE(1, getCardTE(0));

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

    @Override
    public Packet getDescriptionPacket() {
        QDislocatorInitPacket packet = PacketHandler.getPacket(QDislocatorInitPacket.class);
        packet.posX = xCoord;
        packet.posY = yCoord;
        packet.posZ = zCoord;
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        packet.tiledata = nbt;

        return packet.getPacket();
    }
}
