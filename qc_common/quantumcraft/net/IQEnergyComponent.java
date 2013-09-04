package quantumcraft.net;

import net.minecraft.world.World;

public interface IQEnergyComponent {
	public Location[] getPossibleConnections(World w, Location l);
}