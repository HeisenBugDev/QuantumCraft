package quantumcraft.tile;

import net.minecraft.nbt.NBTTagCompound;
import quantumcraft.tile.abstracttiles.TileEnergySource;
import quantumcraft.util.Coords;
import quantumcraft.util.QInterdimensionalGeneratorUtil;

public class TileQInterdimensionalGenerator extends TileEnergySource {
    @Override
    public int getMaxEnergy() {
        return 300;
    }

    @Override
    public int guiID() {
        // TODO - Change this
        return -1;
    }

    @Override
    public void onBlockBreak() {

    }

    public void onQIGChange() {

    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        QInterdimensionalGeneratorUtil.addGenerator(new Coords(xCoord, yCoord, zCoord));
        QInterdimensionalGeneratorUtil.updateAllGenerators(worldObj);
    }
}
