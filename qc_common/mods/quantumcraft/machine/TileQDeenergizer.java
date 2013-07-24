package mods.quantumcraft.machine;

import mods.quantumcraft.core.QDERecipe;
import mods.quantumcraft.core.QRecipeHandler;
import mods.quantumcraft.core.network.PacketHandler;
import mods.quantumcraft.core.network.packets.QDeenergizerInitPacket;
import mods.quantumcraft.inventory.SimpleInventory;
import mods.quantumcraft.machine.abstractmachines.TileEnergySource;
import mods.quantumcraft.machine.abstractmachines.TileMachineBase;
import mods.quantumcraft.net.Location;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;

public class TileQDeenergizer extends TileEnergySource implements
        ISidedInventory{

    public int QEnergyBuffer = 0;
    public int lastItemValue = 0;
    public ItemStack[] inventory = new ItemStack[2];
    int processTime = -1;
    QDERecipe r;
    private SimpleInventory _inv = new SimpleInventory(2, "qde", 64);

    @Override
    public boolean canRotate() {
        return true;
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
    public void onBlockBreak() {
        _inv.dropContents(worldObj, xCoord, yCoord, zCoord);
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
        _inv.setInventorySlotContents(0, inventory[0]);
        _inv.setInventorySlotContents(1, inventory[1]);
    }

    private boolean canProcess() {

        r = QRecipeHandler.getQDERecipeFromInput(inventory[0]);
        boolean flag = true;

        if (inventory[0] == null)
            flag = false;
        if (inventory[0] != null) {
            if (r == null)
                flag = false;
            if (inventory[1] != null) {
                assert r != null;
                if (inventory[1].itemID != r.getOutputItem().itemID)
                    flag = false;
            }
        }

        return flag;

    }

    @Override
    public void updateEntity() {
        if (this.canProcess()) {
            this.lastItemValue = r.getEnergyValue();
            this.QEnergyBuffer = this.lastItemValue;

            if (processTime > 0) processTime--;

            this.QEnergyBuffer =
                    (int) (((float) processTime / (float) r.getProcessTime()) * (float) this.lastItemValue);
            if (this.processTime == 0) process();

            if (this.processTime == -1) processTime = r.getProcessTime();

				/*
                this.QEnergyBuffer = this.QEnergyBuffer
						- (this.lastItemValue / r.getProcessTime());*/
            //_inv.setInventorySlotContents(1, inslot);

        } else {
            processTime = -1;
            QEnergyBuffer = 0;
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

    @Override
    public void onInventoryChanged() {
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

        this.QEnergyBuffer = par1NBTTagCompound.getInteger("QEnergyBuffer");
        this.lastItemValue = par1NBTTagCompound.getInteger("LastItemValue");
        updateNextTick = true;
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("QEnergyBuffer", this.QEnergyBuffer);
        par1NBTTagCompound.setInteger("LastItemValue", this.lastItemValue);
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

        QDeenergizerInitPacket packet = PacketHandler.getPacket(QDeenergizerInitPacket.class);
        packet.posX = xCoord;
        packet.posY = yCoord;
        packet.posZ = zCoord;
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        packet.tiledata = nbt;

        return packet.getPacket();


    }

    public int energyBuffer;

    @Override
    public int getMaxEnergy() {
        return 100000;
    }

    @Override
    public int getCurrentEnergy() {
        return energyBuffer;
    }

    @Override
    public int subtractEnergy(int req) {
        energyBuffer -=req;
        if (energyBuffer <0) energyBuffer = 0;
        if (energyBuffer > getMaxEnergy()) energyBuffer = getMaxEnergy();
        return energyBuffer;
    }
}
