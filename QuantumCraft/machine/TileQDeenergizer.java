package mods.quantumcraft.machine;

import mods.quantumcraft.net.IQEnergySource;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import mods.quantumcraft.core.QRecipeHandler;
import mods.quantumcraft.net.IQEnergySource;

public class TileQDeenergizer extends TileMachineBase implements
		ISidedInventory, IQEnergySource {


	public int QEnergyBuffer = 0;
	public int lastItemValue = 0;
	
	public ItemStack[] inventory = new ItemStack[2];

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

	int processTime = 0;
	
	private void process()
	{
		//we need to put in the QEnergyOutput here
		processTime = -1;
		this.decrStackSize(0, 1);
		if (inventory[1] == null)
		{
			inventory[1] = QRecipeHandler.getQDERecipeFromInput(inventory[0]).getOutputItem();
		} else inventory[1].stackSize++;
	}
	
	private boolean canProcess()
	{
		return QRecipeHandler.getQDERecipeFromInput(inventory[0]) != null
				&& inventory[1].stackSize < inventory[1].getMaxStackSize()
				&& (inventory[1].itemID == QRecipeHandler.getQDERecipeFromInput(inventory[0]).getOutputItem().itemID
				|| inventory[1] == null);
	}
	
	@Override
	public void updateEntity()
	{
        if (this.canProcess())
        {
        	this.lastItemValue = QRecipeHandler.getQDERecipeFromInput(inventory[0]).getEnergyValue();
        	this.QEnergyBuffer = this.lastItemValue;
        	if (this.processTime == 0) process();
        	if (this.processTime == -1) processTime = QRecipeHandler.getQDERecipeFromInput(inventory[0]).getProcessTime();
        	else processTime--;
        	this.QEnergyBuffer = this.QEnergyBuffer - (this.lastItemValue / QRecipeHandler.getQDERecipeFromInput(inventory[0]).getProcessTime());
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
				this.zCoord) != this ? false : entityplayer.getDistanceSq(
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
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		if (var1 == 2 || var1 == 3 || var1 == 4 || var1 == 5) {
			return new int[] { 0, 1 };
		} else
			return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		if (j == 0 || j == 1) {
			return false; // no top & bottom
		} else
			return true; // ask the slot :D
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		if (i == 0)
			return false; // no out from input;
		return true;
	}

	@Override
	public void onInventoryChanged() {
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		System.out.println("NBT sucks more ballz");
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

		this.QEnergyBuffer = par1NBTTagCompound.getShort("QEnergyBuffer");
		this.lastItemValue = par1NBTTagCompound.getShort("LastItemValue");
	}

	/**
	 * Writes a tile entity to NBT.
	 */

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setInteger("QEnergyBuffer",
				(short) this.QEnergyBuffer);
		par1NBTTagCompound.setInteger("LastItemValue",
				(short) this.lastItemValue);
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
	public void rotate() {

	}

	@Override
	public ForgeDirection getDirectionFacing() {
		return null;
	}

}
