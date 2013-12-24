package quantumcraft.net;

import net.minecraft.world.World;
import quantumcraft.util.Location;

public interface IQEnergySink extends IQEnergyComponent {
    public void addSourceToList(World w, Location l, Location source);

    public void replaceSourceList(World w, Location l, EnergySourceList sources);
}
