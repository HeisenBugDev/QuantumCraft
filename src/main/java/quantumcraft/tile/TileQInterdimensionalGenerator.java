package quantumcraft.tile;

import quantumcraft.tile.abstracttiles.TileEnergySource;

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

    public void onQIGChange(){

    }

}
