package quantumcraft.tile;

import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile;
import quantumcraft.core.network.PacketHandler;
import quantumcraft.core.network.packets.QElectrifierInitPacket;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.BlockPosition;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/26/13
 * Time: 8:59 AM
 */
public class TileQElectrifier extends TileEnergySink implements IPowerEmitter, IPipeConnection, IPowerReceptor {
    public float currentOutput = 0;
    protected PowerHandler powerHandler;
    private int tickCounter = 0;
    private boolean redstonePower = false;
    private int energyBuffer = 0;
    private boolean init = false;

    public TileQElectrifier() {
        powerHandler = new PowerHandler(this, PowerHandler.Type.ENGINE);
    }

    @Override
    public int guiID() {
        return -1;
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
            return ((IPowerReceptor) tile).getPowerReceiver(side) != null;
        }

        return false;
    }

    private void sendPower() {
        List<BlockPosition> adjBlocks = new BlockPosition(this.xCoord, this.yCoord, this.zCoord).getAdjacent(true);

        for (int iterator = 0; iterator < adjBlocks.size(); iterator++) {
            TileEntity tile = adjBlocks.get(iterator).getTileEntity(worldObj);
            BlockPosition bTile = adjBlocks.get(iterator);

            if (isPoweredTile(tile, bTile.orientation.getOpposite())) {
                PowerHandler.PowerReceiver receptor =
                        ((IPowerReceptor) tile).getPowerReceiver(bTile.orientation.getOpposite());
                float extracted = getPowerToExtract(tile);
                if (extracted > 0) {
                    float needed =
                            receptor.receiveEnergy(PowerHandler.Type.ENGINE, extracted,
                                    bTile.orientation.getOpposite());

                    //extractEnergy(receptor.getMinEnergyReceived(), needed, true); // Comment out for constant power
                    currentOutput = extractEnergy(0, needed, true); // Uncomment for constant power
                }
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

    private float getPowerToExtract(TileEntity tile) {
        PowerHandler.PowerReceiver receptor =
                ((IPowerReceptor) tile).getPowerReceiver(getDirectionFacing().getOpposite());
        //return extractEnergy(receptor.getMinEnergyReceived(), receptor.getMaxEnergyReceived(), false); // Comment out for constant power
        return extractEnergy(0, (receptor.getMaxEnergyReceived() > 100) ? 100 : receptor.getMaxEnergyReceived(),
                false); // Uncomment for constant power
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
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            //System.out.println("Energy buffer of electrifier: " + energyBuffer);
            this.addEnergy(this.requestPacket(100));
        }

        if (!init && !isInvalid()) {
            initialize();
            init = true;
        }

        if (tickCounter == 0) {
            redstonePower = BasicUtils.isRedstonePowered(this);
            if (!redstonePower) {
                sendPower();
            }
        }
        tickCounter++;
        if (tickCounter >= 10) {
            tickCounter = 0;
        } else currentOutput = 0;

    }

    public void initialize() {
        if (!worldObj.isRemote) {
            powerHandler.configure(minEnergyReceived(), maxEnergyReceived(), 1, getMaxEnergy());
        }
    }

    public float minEnergyReceived() {
        return 2;
    }

    public float maxEnergyReceived() {
        return 50;
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

    @Override
    public ConnectOverride overridePipeConnection(IPipeTile.PipeType type, ForgeDirection with) {
        return ConnectOverride.DEFAULT;
    }
}
