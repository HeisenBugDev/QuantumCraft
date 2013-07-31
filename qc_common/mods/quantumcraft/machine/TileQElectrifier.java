package mods.quantumcraft.machine;

import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.core.TileBuffer;
import mods.quantumcraft.core.network.PacketHandler;
import mods.quantumcraft.core.network.packets.QElectrifierInitPacket;
import mods.quantumcraft.machine.abstractmachines.TileEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/26/13
 * Time: 8:59 AM
 */
public class TileQElectrifier extends TileEnergySink implements IPowerEmitter, IPipeConnection, IPowerReceptor {
    public TileBuffer[] tileCache;
    public boolean isRedstonePowered = false;
    public float currentOutput = 0;
    protected PowerHandler powerHandler;
    private int energyBuffer = 1000;
    private boolean init = false;

    public TileQElectrifier() {
        powerHandler = new PowerHandler(this, PowerHandler.Type.ENGINE);
    }

    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int getCurrentEnergy() {
        return energyBuffer;
    }

    public boolean isPoweredTile(TileEntity tile, ForgeDirection side) {
        if (tile instanceof IPowerReceptor) {
            return ((IPowerReceptor) tile).getPowerReceiver(side.getOpposite()) != null;
        }

        return false;
    }

    private void sendPower() {
        TileEntity tile = tileCache[getDirectionFacing().ordinal()].getTile();
        if (isPoweredTile(tile, getDirectionFacing())) {

            PowerHandler.PowerReceiver receptor =
                    ((IPowerReceptor) tile).getPowerReceiver(getDirectionFacing().getOpposite());
            float extracted = getPowerToExtract();
            if (extracted > 0) {
                float needed =
                        receptor.receiveEnergy(PowerHandler.Type.ENGINE, extracted, getDirectionFacing().getOpposite());
                //extractEnergy(receptor.getMinEnergyReceived(), needed, true); // Comment out for constant power
                currentOutput = extractEnergy(0, needed, true); // Uncomment for constant power

            }
        }
    }

    @Override
    public int subtractEnergy(int req) {
        energyBuffer -= req;
        if (energyBuffer < 0) energyBuffer = 0;
        if (energyBuffer > getMaxEnergy()) energyBuffer = getMaxEnergy();
        return energyBuffer;
    }

    private float getPowerToExtract() {
        TileEntity tile = tileCache[getDirectionFacing().ordinal()].getTile();
        PowerHandler.PowerReceiver receptor =
                ((IPowerReceptor) tile).getPowerReceiver(getDirectionFacing().getOpposite());
        //return extractEnergy(receptor.getMinEnergyReceived(), receptor.getMaxEnergyReceived(), false); // Comment out for constant power
        return extractEnergy(0, receptor.getMaxEnergyReceived(), false); // Uncomment for constant power
    }

    @Override
    public void onBlockBreak() {

    }

    public float maxEnergyExtracted() {
        return 10;
    }

    public float extractEnergy(float min, float max, boolean doExtract) {
        if (energyBuffer < min)
            return 0;

        float actualMax;

        if (max > maxEnergyExtracted()) {
            actualMax = maxEnergyExtracted();
        } else {
            actualMax = max;
        }

        if (actualMax < min)
            return 0;

        float extracted;


        if (energyBuffer >= actualMax) {
            extracted = actualMax;
            if (doExtract) {
                energyBuffer -= actualMax;
            }
        } else {
            extracted = energyBuffer;
            if (doExtract) {
                energyBuffer = 0;
            }
        }

        return extracted;
    }

    @Override
    public void updateEntity() {
        if (!init && !isInvalid()) {
            initialize();
            init = true;
        }
        if (isRedstonePowered) {
            sendPower();
        } else currentOutput = 0;

    }

    public void initialize() {
        if (!worldObj.isRemote) {
            tileCache = TileBuffer.makeBuffer(worldObj, xCoord, yCoord, zCoord, true);
            powerHandler.configure(minEnergyReceived(), maxEnergyReceived(), 1, getMaxEnergy());
            checkRedstonePower();
        }
    }

    public float minEnergyReceived() {
        return 2;
    }

    public float maxEnergyReceived() {
        return 50;
    }

    public void checkRedstonePower() {
        isRedstonePowered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.energyBuffer = par1NBTTagCompound.getInteger("energyBuffer");
        updateNextTick = true;
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("energyBuffer", this.energyBuffer);

        super.writeToNBT(par1NBTTagCompound);
    }

    @Override
    public Packet getDescriptionPacket() {
        QElectrifierInitPacket packet = PacketHandler.getPacket(QElectrifierInitPacket.class);
        packet.posX = xCoord;
        packet.posY = yCoord;
        packet.posZ = zCoord;
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        packet.tiledata = nbt;

        return packet.getPacket();
    }

    @Override
    public boolean canEmitPowerFrom(ForgeDirection side) {
        return true;
    }

    @Override
    public boolean isPipeConnected(ForgeDirection with) {
        return true;
    }

    @Override
    public PowerHandler.PowerReceiver getPowerReceiver(ForgeDirection side) {
        return null;
    }

    @Override
    public void doWork(PowerHandler workProvider) {

    }

    @Override
    public World getWorld() {
        return null;
    }
}
