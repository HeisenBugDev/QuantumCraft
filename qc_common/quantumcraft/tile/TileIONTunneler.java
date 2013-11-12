package quantumcraft.tile;

import quantumcraft.tile.abstracttiles.TileEnergySink;

public class TileIONTunneler extends TileEnergySink {
    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int guiID() {
        return 7;
    }

    @Override
    public void onBlockBreak() {

    }

    @Override
    public void updateEntity() {
        System.out.println("The coords are: " + xCoord + " " + yCoord + " " + zCoord);
    }
}
