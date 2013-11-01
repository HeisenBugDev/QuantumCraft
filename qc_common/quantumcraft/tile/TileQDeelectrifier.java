package quantumcraft.tile;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.Type;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import quantumcraft.tile.abstracttiles.TileEnergySource;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 9/2/13
 * Time: 3:29 PM
 */
public class TileQDeelectrifier extends TileEnergySource implements IPowerReceptor {
    private int tickCounter = 0;
    private boolean redstonePower = false;
    private PowerHandler powerHandler;
    private int buildCraftBuffer = 0;

    public TileQDeelectrifier() {
        powerHandler = new PowerHandler(this, Type.MACHINE);
        powerHandler.configure(5, 100, 2, 1000);
        powerHandler.configurePowerPerdition(1, 1);
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

    @Override
    public void updateEntity() {
        buildCraftBuffer += powerHandler.useEnergy(1, 100, true);
        //System.out.println("Powerhandler use energy: " + powerHandler.useEnergy(1,100,false));
        //System.out.println("Buildcraft buffer: " + buildCraftBuffer + " Energy Buffer:  " + energyBuffer);
        if (buildCraftBuffer >= 1) {
            addEnergy(1);
            buildCraftBuffer--;
        }
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
        return worldObj;
    }
}
