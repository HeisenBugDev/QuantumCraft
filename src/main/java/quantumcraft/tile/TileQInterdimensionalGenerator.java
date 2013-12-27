package quantumcraft.tile;

import net.minecraft.nbt.NBTTagCompound;
import quantumcraft.tile.abstracttiles.TileEnergySource;
import quantumcraft.util.Coords;
import quantumcraft.util.QIGUtil;

public class TileQInterdimensionalGenerator extends TileEnergySource {
    @Override
    public int getMaxEnergy() {
        return 0;
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
        QIGUtil.addGenerator(new Coords(xCoord, yCoord, zCoord));
    }
}
