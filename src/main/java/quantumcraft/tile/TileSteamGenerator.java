package quantumcraft.tile;

import quantumcraft.tile.abstracttiles.TileMachineBase;

public class TileSteamGenerator extends TileMachineBase{
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
}
