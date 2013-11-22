package quantumcraft.tile;

import quantumcraft.tile.abstracttiles.TileEnergySink;

public class TileQCapacitor extends TileEnergySink {
    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int guiID() {
        return 0;
    }

    @Override
    public void onBlockBreak() {

    }

    @Override
    public void updateEntity() {
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(100));
        }
    }

}
