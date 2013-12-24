package quantumcraft.net;

import net.minecraft.world.World;
import quantumcraft.util.Location;

public interface IQEnergySource extends IQEnergyComponent {
    public int requestQuantumEnergy(World w, Location l, int request);
}
