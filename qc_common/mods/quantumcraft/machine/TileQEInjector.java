package mods.quantumcraft.machine;

import mods.quantumcraft.core.interfaces.IQEnergizable;
import mods.quantumcraft.core.network.PacketHandler;
import mods.quantumcraft.core.network.packets.QEInjectorInitPacket;
import mods.quantumcraft.inventory.SimpleInventory;
import mods.quantumcraft.machine.abstractmachines.TileEnergySink;
import mods.quantumcraft.machine.abstractmachines.TileMachineBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;

public class TileQEInjector extends TileEnergySink implements
        ISidedInventory{

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

    public void process()
    {
        inventory[1] = inventory[0].copy();
        decrStackSize(0,1);
    }

    @Override
    public String getInvName() {
        return "Quantum Energy Injector";
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

    //I think this method would like a refactor, but meh. if you have the nerves to do it, go ahead. AND DO NOT BREAK IT
    @Override
    public void updateEntity() {
        if (inventory[0] != null) {
            if (inventory[0].getItem() instanceof IQEnergizable) {
                IQEnergizable e = ((IQEnergizable) inventory[0].getItem());
                int cycle = 5;
                this.maxival = e.getMaxQEnergyValue();
                this.currentival = e.getCurrentQEnergyBuffer();
                if (e.getCurrentQEnergyBuffer() <= (e.getMaxQEnergyValue()-cycle)) {
                    if (this.getCurrentEnergy() < cycle) {
                        cycle = this.getCurrentEnergy();
                    }
                    if (cycle != 0) {
                        e.setCurrentQEnergyBuffer(e.getCurrentQEnergyBuffer()+cycle);
                    }
                } else {
                    cycle = e.getMaxQEnergyValue() - e.getCurrentQEnergyBuffer();
                    if (this.getCurrentEnergy() < cycle) {
                        cycle = this.getCurrentEnergy();
                    }
                    if (cycle != 0) {
                        e.setCurrentQEnergyBuffer(e.getCurrentQEnergyBuffer()+cycle);
                    }
                }
                this.subtractEnergy(cycle);
                this.addEnergy(this.requestPacket(this.getMaxEnergy() - this.getCurrentEnergy()));
                if (e.getCurrentQEnergyBuffer() == e.getMaxQEnergyValue()) {
                    process();
                }
            }
        }
    }

    private int energyBuffer;

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
    public Packet getDescriptionPacket() {
        QEInjectorInitPacket packet = PacketHandler.getPacket(QEInjectorInitPacket.class);
        packet.posX = xCoord;
        packet.posY = yCoord;
        packet.posZ = zCoord;
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        packet.tiledata = nbt;

        return packet.getPacket();
    }

    @Override
    public int getMaxEnergy() {
        return 5000;
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
