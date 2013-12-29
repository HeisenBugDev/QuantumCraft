package quantumcraft.tile;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.Type;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import quantumcraft.core.QuantumCraft;
import quantumcraft.tile.abstracttiles.TileEnergySource;

public class TileQDeelectrifier extends TileEnergySource implements IPowerReceptor {
    private PowerHandler powerHandler;
    private float buildCraftBuffer = 0;

    public TileQDeelectrifier() {
        powerHandler = new PowerHandler(this, Type.MACHINE);
        powerHandler.configure(1, 100, 60, 1000);
        powerHandler.configurePowerPerdition(0, 0);
    }

    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int guiID() {
        return -1;
    }

    @Override
    public void onBlockBreak() {

    }

    @Override public String getStatusText() {
        return buildCraftBuffer + " MJ";
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        buildCraftBuffer += powerHandler.useEnergy(1, 100, true);
        int powerUse = 100;
        if (powerUse > buildCraftBuffer) powerUse = (int) buildCraftBuffer;
        if (powerUse > getMaxEnergy() - getCurrentEnergy()) powerUse = getMaxEnergy() - getCurrentEnergy();
        if (buildCraftBuffer >= 1) {
            addEnergy(powerUse);
            buildCraftBuffer -= powerUse;
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    @Override
    public PowerHandler.PowerReceiver getPowerReceiver(ForgeDirection side) {
        return powerHandler.getPowerReceiver();
    }

    @Override
    public void doWork(PowerHandler workProvider) {
        QuantumCraft.logHandler.debugPrint(this, "" + this.getCurrentEnergy());
    }

    @Override
    public World getWorld() {
        return worldObj;
    }
}
