package quantumcraft.util;

import quantumcraft.tile.TileQInterdimensionalGenerator;

import java.util.HashSet;
import java.util.Set;

public class QInterdimensionalGeneratorUtil {
    private static Set<QInterdimensionalGeneratorDataObject> generators =
            new HashSet<QInterdimensionalGeneratorDataObject>();

    public static Set<QInterdimensionalGeneratorDataObject> getGenerators() {
        return generators;
    }

    public static void setGenerators(HashSet<QInterdimensionalGeneratorDataObject> generators) {
        QInterdimensionalGeneratorUtil.generators = generators;
    }

    public static boolean addGenerator(QInterdimensionalGeneratorDataObject QIGData) {
        return generators.add(QIGData);
    }

    public static Boolean removeGenerator(QInterdimensionalGeneratorDataObject generator) {
        return generators.remove(generator);
    }

    public static void removeGeneratorFromNewCoords(QInterdimensionalGeneratorDataObject generatorParam) {
        for (QInterdimensionalGeneratorDataObject generator : generators) {
            if (generator.getCoords().compareCoords(generatorParam.getCoords()) &&
                    (generatorParam.getWorld() == generator.getWorld())) generators.remove(generator);
        }
    }

    public static void clearGenerators() {
        generators.clear();
    }

    public static void updateAllGenerators() {
        for (QInterdimensionalGeneratorDataObject generator : generators) {
            TileQInterdimensionalGenerator tile = (TileQInterdimensionalGenerator) BasicUtils
                    .getTileEntity(generator.getWorld(), generator.getCoords(), TileQInterdimensionalGenerator.class);
            if (tile != null) tile.onQIGChange();
        }
    }
}
