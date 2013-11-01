package quantumcraft.tile;

import quantumcraft.tile.abstracttiles.TileEnergySink;

public class TileIONForge extends TileEnergySink {
    private int energyBuffer = 0;

    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int guiID() {
        return -1;
    }

    //TODO NBT STUFF!

    @Override
    public void onBlockBreak() {
        //TODO Inventory
    }

    @Override
    public void updateEntity() {

    }

}
