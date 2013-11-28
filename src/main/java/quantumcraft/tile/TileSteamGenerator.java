package quantumcraft.tile;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.*;
import quantumcraft.tile.abstracttiles.TileMachineBase;

public class TileSteamGenerator extends TileMachineBase implements IFluidHandler{
    private FluidTank tank = new FluidTank(12000);

    @Override
    public int getMaxEnergy() {
        return 0;
    }

    @Override
    public int guiID() {
        // Will be 8 when done
        return -1;
    }

    @Override
    public void onBlockBreak() {

    }

    @Override
    public int fill(ForgeDirection forgeDirection, FluidStack fluidStack, boolean b) {
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection forgeDirection, FluidStack fluidStack, boolean b) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection forgeDirection, int i, boolean b) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection forgeDirection, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection forgeDirection, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection forgeDirection) {
        return new FluidTankInfo[0];
    }
}
