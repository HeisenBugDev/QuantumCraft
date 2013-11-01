package quantumcraft.tile.abstracttiles;

import net.minecraft.nbt.NBTTagCompound;
import quantumcraft.net.Location;

public abstract class TileEnergySource extends TileMachineBase {


    public int getQuantumEnergy(Location l, int request) {
        if (getCurrentEnergy() >= request) {
            subtractEnergy(request);
            return request;
        } else {
            int e = getCurrentEnergy();
            subtractEnergy(request);
            return e;
        }
    }
}
