package quantumcraft.util;

import net.minecraft.world.IBlockAccess;
import quantumcraft.tile.TileQInterdimensionalGenerator;

import java.util.HashSet;
import java.util.Set;

public class QIGUtil {
    private static Set<Coords> generators = new HashSet<Coords>();

    public static Set<Coords> getGenerators() {
        return generators;
    }

    public static void setGenerators(HashSet<Coords> generators) {
        QIGUtil.generators = generators;
    }

    public static boolean addGenerator(Coords Coords) {
        return generators.add(Coords);
    }

    public static Boolean removeGenerator(Coords coords) {
        return generators.remove(coords);
    }

    public static void removeGeneratorFromNewCoords(Coords coords) {
        for (Coords generator : generators) {
            if (generator.compareCoords(coords)) generators.remove(generator);
        }
    }

    public static void clearGenerators() {
        generators.clear();
    }

    public static void updateAllGenerators(IBlockAccess access) {
        for (Coords generator : generators) {
            TileQInterdimensionalGenerator tile = (TileQInterdimensionalGenerator) BasicUtils
                    .getTileEntity(access, generator, TileQInterdimensionalGenerator.class);
            if (tile != null) {
                tile.onQIGChange();
            }
        }
    }
}
