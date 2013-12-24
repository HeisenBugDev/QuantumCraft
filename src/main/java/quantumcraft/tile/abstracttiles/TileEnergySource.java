package quantumcraft.tile.abstracttiles;

import quantumcraft.util.Coords;

public abstract class TileEnergySource extends TileMachineBase {


    public int requestQuantumEnergy(Coords l, int request) {
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
