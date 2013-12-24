package quantumcraft.tile.abstracttiles;

import quantumcraft.util.Location;

public abstract class TileEnergySource extends TileMachineBase {


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
}
