package quantumcraft.util;

import net.minecraft.world.World;

public class QInterdimensionalGeneratorDataObject {
    private World world;
    private Coords coords;

    public QInterdimensionalGeneratorDataObject(World worldObj, Coords localCoords) {
        world = worldObj;
        coords = localCoords;
    }

    public World getWorld() {
        return world;
    }

    public Coords getCoords() {
        return coords;
    }
}
