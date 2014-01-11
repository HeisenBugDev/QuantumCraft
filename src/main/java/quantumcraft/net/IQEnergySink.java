package quantumcraft.net;

import net.minecraft.world.World;

public interface IQEnergySink extends IQEnergyComponent {
    public void addSourceToList(World w, Location l, Location source);

    public void replaceSourceList(World w, Location l, EnergySourceList sources);
}
