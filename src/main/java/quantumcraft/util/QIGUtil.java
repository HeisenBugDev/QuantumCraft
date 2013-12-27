package quantumcraft.util;

import net.minecraft.world.IBlockAccess;
import quantumcraft.tile.TileQInterdimensionalGenerator;

import java.util.ArrayList;

public class QIGUtil {
    private static ArrayList<Coords> generators = new ArrayList<Coords>();

    public static ArrayList<Coords> getGenerators() {
        return generators;
    }

    public static void setGenerators(ArrayList<Coords> generators) {
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
