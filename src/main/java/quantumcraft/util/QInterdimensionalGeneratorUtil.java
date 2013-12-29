package quantumcraft.util;

import net.minecraft.world.IBlockAccess;
import quantumcraft.tile.TileQInterdimensionalGenerator;

import java.util.ArrayList;
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
        if (QIGData == null) return false;
        for (QInterdimensionalGeneratorDataObject generator : generators) {
            if (QIGData.getCoords().x == generator.getCoords().x &&
                    QIGData.getCoords().y == generator.getCoords().y &&
                    QIGData.getCoords().z == generator.getCoords().z) return false;
        }
        return generators.add(QIGData);
    }

    public static Boolean removeGenerator(QInterdimensionalGeneratorDataObject generator) {
        return generators.remove(generator);
    }

    public static void removeGeneratorFromNewCoords(QInterdimensionalGeneratorDataObject generatorParam,
                                                    IBlockAccess access) {
        Set<QInterdimensionalGeneratorDataObject> tmpList = new HashSet<QInterdimensionalGeneratorDataObject>();
        for (QInterdimensionalGeneratorDataObject generator : generators) {
            if (generator.getCoords().compareCoords(generatorParam.getCoords())) {
                tmpList.add(generator);
                System.out.println("removeGenFromNewCoords");
            }
        }

        for (QInterdimensionalGeneratorDataObject generator : tmpList) {
            System.out.println(generators.remove(generator));
        }
        updateAllGenerators(access);
    }

    public static void clearGenerators() {
        generators.clear();
    }

    public static void updateAllGenerators(IBlockAccess access) {
        ArrayList<TileQInterdimensionalGenerator> tmpList = new ArrayList<TileQInterdimensionalGenerator>();
        for (QInterdimensionalGeneratorDataObject generator : generators) {
            TileQInterdimensionalGenerator tile = (TileQInterdimensionalGenerator) BasicUtils
                    .getTileEntity(access, generator.getCoords(), TileQInterdimensionalGenerator.class);
            tmpList.add(tile);
        }

        for (TileQInterdimensionalGenerator tile : tmpList) {
            System.out.println(tile);
            if (tile != null) tile.onQIGChange();
        }
    }
}
