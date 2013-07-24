package mods.quantumcraft.net;

import net.minecraft.world.World;

public interface IQEnergySource extends IQEnergyComponent{
	public int getQuantumEnergy(World w, Location l, int request);
}
