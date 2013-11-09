package quantumcraft.tile;

import quantumcraft.tile.abstracttiles.TileEnergySink;

public class TileQEnergySucker extends TileEnergySink {
    @Override
    public int getMaxEnergy() {
        return 10;
    }

    @Override
    public int getCurrentEnergy() {
        return 0;
    }

    @Override
    public int subtractEnergy(int req) {
        return 0;
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
        this.requestPacket(10);
    }

}
