package quantumcraft.util;

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

    public static Coords removeGenerator(int index) {
        return generators.remove(index);
    }

    public static void clearGenerators() {
        generators.clear();
    }
}
