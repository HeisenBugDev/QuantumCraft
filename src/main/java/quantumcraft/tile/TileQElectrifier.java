package quantumcraft.tile;

import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.BlockPosition;

import java.util.List;

public class TileQElectrifier extends TileEnergySink implements IPowerEmitter, IPipeConnection, IPowerReceptor {
    public float currentOutput = 0;
    protected PowerHandler powerHandler;
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
        return tile instanceof IPowerReceptor && ((IPowerReceptor) tile).getPowerReceiver(side) != null;

    }

    @Override public String getStatusText() {
        return currentOutput + " MJ/t";
    }

    private void sendPower() {
        List<BlockPosition> adjBlocks = new BlockPosition(this.xCoord, this.yCoord, this.zCoord).getAdjacent(true);
        boolean powerNeeded = false;
        for (BlockPosition adjBlock : adjBlocks) {
            TileEntity tile = adjBlock.getTileEntity(worldObj);

            if (isPoweredTile(tile, adjBlock.orientation.getOpposite())) {
                PowerHandler.PowerReceiver receptor =
                        ((IPowerReceptor) tile).getPowerReceiver(adjBlock.orientation.getOpposite());
                float extracted = getPowerToExtract(tile);
                if (extracted > 0) {
                    float needed = receptor.receiveEnergy(PowerHandler.Type.ENGINE, extracted,
                            adjBlock.orientation.getOpposite());
                    powerNeeded = true;
                    //extractEnergy(receptor.getMinEnergyReceived(), needed, true); // Comment out for constant power
                    currentOutput = extractEnergy(0, needed, true); // Uncomment for constant power
                }
            }
        }
        if (!powerNeeded) currentOutput = 0;
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
        if (getCurrentEnergy() < min) return 0;

        float actualMax;

        if (max > maxEnergyExtracted()) {
            actualMax = maxEnergyExtracted();
        } else {
            actualMax = max;
        }

        if (actualMax < min) return 0;

        float extracted;


        if (getCurrentEnergy() >= actualMax) {
            extracted = actualMax;
            if (doExtract) {
                subtractEnergy((int) actualMax);
            }
        } else {
            extracted = 0;
        }

        return extracted;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (redstonePower) return;
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(100));
        } else if (this.getCurrentEnergy() > this.getMaxEnergy()) {
            this.setEnergy(this.getMaxEnergy());
        }
        if (!init && !isInvalid()) {
            initialize();
            init = true;
        }

        boolean redstonePower = BasicUtils.isRedstonePowered(this);
        if (!redstonePower) {
            sendPower();
        } else currentOutput = 0;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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
        return powerHandler.getPowerReceiver();
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
