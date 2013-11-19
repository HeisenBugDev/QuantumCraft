package quantumcraft.tile;

import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.BlockPosition;

import java.util.List;

public class TileQElectrifier extends TileEnergySink implements IPowerEmitter, IPipeConnection, IPowerReceptor {
    public float currentOutput = 0;
    protected PowerHandler powerHandler;
    private int tickCounter = 0;
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
        if (getCurrentEnergy() < min)
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


        if (getCurrentEnergy() >= actualMax) {
            extracted = actualMax;
            if (doExtract) {
                subtractEnergy((int) actualMax);
            }
        } else {
            extracted = getCurrentEnergy();
            if (doExtract) {
                setEnergy(0);
            }
        }

        return extracted;
    }

    @Override
    public void updateEntity() {
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(10));
        }

        if (!init && !isInvalid()) {
            initialize();
            init = true;
        }

        if (tickCounter == 0) {
            boolean redstonePower = BasicUtils.isRedstonePowered(this);
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
