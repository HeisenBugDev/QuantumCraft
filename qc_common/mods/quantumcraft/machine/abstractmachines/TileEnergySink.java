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
}
