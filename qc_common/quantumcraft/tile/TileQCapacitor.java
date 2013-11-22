package quantumcraft.tile;

import quantumcraft.net.Location;
import quantumcraft.tile.abstracttiles.TileEnergySink;

public class TileQCapacitor extends TileEnergySink {
    @Override
    public int getMaxEnergy() {
        return 100000;
    }

    @Override
    public int guiID() {
        return 0;
    }

    @Override
    public void onBlockBreak() {

    }

    public int requestQuantumEnergy(Location l, int request) {
        if (getCurrentEnergy() >= request) {
            subtractEnergy(request);
            updateNextTick = true;
            return request;
        } else {
            int e = getCurrentEnergy();
            subtractEnergy(request);
            updateNextTick = true;
            return e;
        }
    }

    @Override
    public void updateEntity() {
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(100));
        }
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

}
