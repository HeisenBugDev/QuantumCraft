package quantumcraft.net;

import net.minecraft.world.World;
import quantumcraft.util.Coords;

public interface IQEnergySource extends IQEnergyComponent {
    public int requestQuantumEnergy(World w, Coords l, int request);
}
