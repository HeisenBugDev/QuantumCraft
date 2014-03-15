package quantumcraft.util;

import net.minecraft.world.IBlockAccess;
import quantumcraft.tile.TileQuantumInterdimensionalGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class QuantumInterdimensionalGeneratorUtil {

    private static Set<QuantumInterdimensionalGeneratorDataObject> generators =
            new HashSet<QuantumInterdimensionalGeneratorDataObject>();

    public static Set<QuantumInterdimensionalGeneratorDataObject> getGenerators() {
        return generators;
    }

    public static void setGenerators(HashSet<QuantumInterdimensionalGeneratorDataObject> generators) {
        QuantumInterdimensionalGeneratorUtil.generators = generators;
    }

    public static boolean addGenerator(QuantumInterdimensionalGeneratorDataObject QIGData) {
        if (QIGData == null) return false;
        for (QuantumInterdimensionalGeneratorDataObject generator : generators) {
            if (QIGData.getCoords().x == generator.getCoords().x &&
                    QIGData.getCoords().y == generator.getCoords().y &&
                    QIGData.getCoords().z == generator.getCoords().z) return false;
        }
        return generators.add(QIGData);
    }

    public static Boolean removeGenerator(QuantumInterdimensionalGeneratorDataObject generator) {
        return generators.remove(generator);
    }

    public static void removeGeneratorFromNewCoords(QuantumInterdimensionalGeneratorDataObject generatorParam,
                                                    IBlockAccess access) {
        Set<QuantumInterdimensionalGeneratorDataObject> tmpList = new HashSet<QuantumInterdimensionalGeneratorDataObject>();
        for (QuantumInterdimensionalGeneratorDataObject generator : generators) {
            if (generator.getCoords().compareCoords(generatorParam.getCoords())) {
                tmpList.add(generator);
                System.out.println("removeGenFromNewCoords");
            }
        }

        for (QuantumInterdimensionalGeneratorDataObject generator : tmpList) {
            System.out.println(generators.remove(generator));
        }
        updateAllGenerators(access);
    }

    public static void clearGenerators() {
        generators.clear();
    }

    public static void updateAllGenerators(IBlockAccess access) {
        ArrayList<TileQuantumInterdimensionalGenerator> tmpList = new ArrayList<TileQuantumInterdimensionalGenerator>();
        for (QuantumInterdimensionalGeneratorDataObject generator : generators) {
            TileQuantumInterdimensionalGenerator tile = (TileQuantumInterdimensionalGenerator) BasicUtils
                    .getTileEntity(access, generator.getCoords(), TileQuantumInterdimensionalGenerator.class);
            tmpList.add(tile);
        }

        for (TileQuantumInterdimensionalGenerator tile : tmpList) {
            System.out.println(tile);
            if (tile != null) tile.onQIGChange();
        }
    }
}
