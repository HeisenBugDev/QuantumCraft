package quantumcraft.tile.abstracttiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import quantumcraft.core.QuantumCraft;
import quantumcraft.core.interfaces.IInWorldGui;
import quantumcraft.core.interfaces.IRotateableTile;
import quantumcraft.core.network.PacketHandler;
import quantumcraft.core.network.packets.MachineInitPacket;
import quantumcraft.util.BasicUtils;

public abstract class TileMachineBase extends TileEntity implements IRotateableTile, IInWorldGui {

    public int upgradeID[] = {0, 0, 0, 0};
    public boolean updateNextTick = false;
    private ForgeDirection _forwardDirection;
    private int energyBuffer = 0;
    public boolean redstonePower = false;

    protected TileMachineBase() {
        _forwardDirection = ForgeDirection.NORTH;
    }

    @Override
    public String getPowerInfo() {
        if (this.getMaxEnergy() == 0) return "N/A";
        String chatColor = "";
        int powerPercent = (this.getCurrentEnergy() * 100) / this.getMaxEnergy();
        if (powerPercent > 66) {
            chatColor = EnumChatFormatting.GREEN.toString();
        } else if (powerPercent >= 33) {
            chatColor = EnumChatFormatting.DARK_BLUE.toString();
        } else {
            chatColor = EnumChatFormatting.RED.toString();
        }
        return chatColor + this.getCurrentEnergy() + " / " + this.getMaxEnergy() + " (" + powerPercent + "%)";
    }

    public String getStatusText() {
        return EnumChatFormatting.RED + "Unknown";
    }

    /**
     * @return current energy the machine is holding
     */
    public int getCurrentEnergy() {
        return energyBuffer;
    }

    /**
     * @return maximum energy this machine can hold
     */
    public abstract int getMaxEnergy();

    /**
     * @param set the value you want to set the energy to
     */
    public void setEnergy(int set) {
        energyBuffer = set;
    }

    /**
     * Adds energy to current buffer. uses substractEnergy with negative nubers.
     *
     * @param req amount to add
     * @return energy buffer _AFTER_ addition
     */
    public int addEnergy(int req) {
        return subtractEnergy(-req);
    }

    /**
     * Subtracts energy from current buffer. INCLUDE ZERO AND MAXCHECKING
     *
     * @param req amount to subtract
     * @return energy buffer _AFTER_ subtraction
     */
    public int subtractEnergy(int req) {
        energyBuffer -= req;
        if (energyBuffer < 0) energyBuffer = 0;
        if (energyBuffer > getMaxEnergy()) energyBuffer = getMaxEnergy();
        return energyBuffer;
    }

    @Override
    public Packet getDescriptionPacket() {
        MachineInitPacket packet = PacketHandler.getPacket(MachineInitPacket.class);
        packet.posX = xCoord;
        packet.posY = yCoord;
        packet.posZ = zCoord;
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        packet.tiledata = nbt;

        return packet.getPacket();


    }

    public abstract int guiID();

    public abstract void onBlockBreak();

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.energyBuffer = nbttagcompound.getInteger("energyBuffer");
        updateNextTick = true;
        int rotation = nbttagcompound.getInteger("rotation");
        rotateDirectlyTo(rotation);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("energyBuffer", this.energyBuffer);
        nbttagcompound.setInteger("rotation", getDirectionFacing().ordinal());
    }

    @Override
    public ForgeDirection getDirectionFacing() {
        return _forwardDirection;
    }

    public ForgeDirection getDropDirection() {
        return getDirectionFacing().getOpposite();
    }

    public int getRotatedSide(int side) {
        if (side < 2) {
            return side;
        } else if (_forwardDirection == ForgeDirection.EAST) {
            return addToSide(side, 1);
        } else if (_forwardDirection == ForgeDirection.SOUTH) {
            return addToSide(side, 2);
        } else if (_forwardDirection == ForgeDirection.WEST) {
            return addToSide(side, 3);
        }
        return side;
    }

    private int addToSide(int side, int shift) {
        int shiftsRemaining = shift;
        int out = side;
        while (shiftsRemaining > 0) {
            if (out == 2) out = 4;
            else if (out == 4) out = 3;
            else if (out == 3) out = 5;
            else if (out == 5) out = 2;
            shiftsRemaining--;
        }
        return out;
    }

    public boolean canRotate() {
        return true;
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
            PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 50, worldObj.provider.dimensionId,
                    getDescriptionPacket());
        }
    }

    @Override
    public void updateEntity() {
        if (worldObj.getWorldTime() % 20 == 0) {
            redstonePower = BasicUtils.isRedstonePowered(this);
            QuantumCraft.logHandler.debugPrint(this, "Current Energy is: " + this.getCurrentEnergy());
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    public boolean useRotated() {
        //NORTH and SOUTH = false
        //WEST and EAST = true
        return !(_forwardDirection == ForgeDirection.WEST || _forwardDirection == ForgeDirection.EAST);
    }

    public void rotateDirectlyTo(int rotation) {
        _forwardDirection = ForgeDirection.getOrientation(rotation);
        if (worldObj != null) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }
}
