package quantumcraft.net;

import net.minecraft.world.World;
import quantumcraft.util.Coords;

public interface IQEnergySink extends IQEnergyComponent {
    public void addSourceToList(World w, Coords l, Coords source);

    public void replaceSourceList(World w, Coords l, EnergySourceList sources);
}
