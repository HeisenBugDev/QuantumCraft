package mods.quantumcraft.machine;

import mods.quantumcraft.core.interfaces.IRotateableTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.network.PacketDispatcher;

public abstract class TileMachineBase extends TileEntity implements
		IRotateableTile {

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		int rotation = nbttagcompound.getInteger("rotation");
		rotateDirectlyTo(rotation);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("rotation", getDirectionFacing().ordinal());
	}

	@Override
	public ForgeDirection getDirectionFacing() {
		return _forwardDirection;
	}

	private ForgeDirection _forwardDirection;

	public ForgeDirection getDropDirection() {
		return getDirectionFacing().getOpposite();
	}

	public int getRotatedSide(int side) {
		if (side < 2) {
			return side;
		} else if (_forwardDirection == ForgeDirection.WEST) {
			return addToSide(side, 1);
		} else if (_forwardDirection == ForgeDirection.NORTH) {
			return addToSide(side, 2);
		} else if (_forwardDirection == ForgeDirection.EAST) {
			return addToSide(side, 3);
		}
		return side;
	}

	private int addToSide(int side, int shift) {
		int shiftsRemaining = shift;
		int out = side;
		while (shiftsRemaining > 0) {
			if (out == 2)
				out = 4;
			else if (out == 4)
				out = 3;
			else if (out == 3)
				out = 5;
			else if (out == 5)
				out = 2;
			shiftsRemaining--;
		}
		return out;
	}

	public boolean canRotate() {
		return false;
	}

	@Override
	public void rotate() {
		if (!worldObj.isRemote) {
			if (_forwardDirection == ForgeDirection.NORTH) {
				_forwardDirection = ForgeDirection.EAST;
			} else if (_forwardDirection == ForgeDirection.EAST) {
				_forwardDirection = ForgeDirection.SOUTH;
			} else if (_forwardDirection == ForgeDirection.SOUTH) {
				_forwardDirection = ForgeDirection.WEST;
			} else if (_forwardDirection == ForgeDirection.WEST) {
				_forwardDirection = ForgeDirection.NORTH;
			} else {
				_forwardDirection = ForgeDirection.NORTH;
			}

			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 50,
					worldObj.provider.dimensionId, getDescriptionPacket());
		}
	}

	public void rotateDirectlyTo(int rotation) {
		_forwardDirection = ForgeDirection.getOrientation(rotation);
		if (worldObj != null) {
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
}
