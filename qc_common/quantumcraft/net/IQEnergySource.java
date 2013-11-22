package quantumcraft.net;

import net.minecraft.world.World;

public interface IQEnergySource extends IQEnergyComponent {
    public int requestQuantumEnergy(World w, Location l, int request);
}
