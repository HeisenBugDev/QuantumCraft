package mods.quantumcraft.machine.abstractmachines;

import mods.quantumcraft.net.EnergySourceList;
import mods.quantumcraft.net.Location;

public abstract class TileEnergySink extends TileMachineBase {

    private EnergySourceList sourceList;

    public void addSourceToList(Location l, Location source) {
        sourceList.addSource(source);
    }

    public void replaceSourceList(Location l, EnergySourceList sources) {
        sourceList = sources;
    }

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
     * @param req amount to add
     * @return energy buffer _AFTER_ addition
     */
    public int addEnergy(int req) {
        return subtractEnergy(-req);
    }

    /**
     * Subtracts energy from current buffer. INCLUDE ZERO AND MAXCHECKING
     * @param req amount to subtract
     * @return energy buffer _AFTER_ subtraction
     */
    public abstract int subtractEnergy(int req);

    /**
     * Method to receive an energy packet from an energy source
     * @param size size of the requested packet
     * @return actual amount of energy received
     */
    public int requestPacket(int size) {
        return sourceList.getQuantumEnergy(worldObj, size);
    }
}
