package quantumcraft.tile;

import quantumcraft.net.Location;
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

    public int requestQuantumEnergy(Location l, int request) {
        if (getCurrentEnergy() >= request) {
            subtractEnergy(request);
            return request;
        } else {
            int e = getCurrentEnergy();
            subtractEnergy(request);
            return e;
        }
    }

    @Override
    public void updateEntity() {
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(100));
        }
        System.out.println(this.getCurrentEnergy());
    }

}
