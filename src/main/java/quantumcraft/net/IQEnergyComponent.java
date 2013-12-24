package quantumcraft.net;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import quantumcraft.util.Coords;

public interface IQEnergyComponent {
    public Coords[] getPossibleConnections(World w, Coords l);

    public boolean canFiberConnectOnSide(IBlockAccess w, int x, int y, int z, int side);
}