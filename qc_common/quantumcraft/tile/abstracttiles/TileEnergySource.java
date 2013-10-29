package quantumcraft.tile.abstracttiles;

import quantumcraft.net.Location;

public abstract class TileEnergySource extends TileMachineBase {

    /**
     * @return maximum energy this machine can hold
     */
    public abstract int getMaxEnergy();

    /**
     * @return current energy the machine is holding
     */
    public abstract int getCurrentEnergy();

    /**
     * Adds energy to current buffer. uses substractEnergy with negative nubers.
     *
     * @param req amount to add
     * @return energy buffer _AFTER_ addition
     */
    public int addEnergy(int req) {
        return subtractEnergy(-req);
    }

    /**
     * Subtracts energy from current buffer. INCLUDE ZERO AND MAXCHECKING
     *
     * @param req amount to subtract
     * @return energy buffer _AFTER_ subtraction
     */
    public abstract int subtractEnergy(int req);

    public int getQuantumEnergy(Location l, int request) {
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
