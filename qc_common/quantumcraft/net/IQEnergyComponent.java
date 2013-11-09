package quantumcraft.net;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IQEnergyComponent {
    public Location[] getPossibleConnections(World w, Location l);

    public boolean canFiberConnectOnSide(IBlockAccess w, int x, int y, int z, int side);
}