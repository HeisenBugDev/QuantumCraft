package quantumcraft.util;

import java.util.ArrayList;

public class QIGUtil {
    private static ArrayList<Location> generators = new ArrayList<Location>();

    public static ArrayList<Location> getGenerators() {
        return generators;
    }
    
    public static void setGenerators(ArrayList<Location> generators) {
        QIGUtil.generators = generators;
    }

    public static boolean addGenerator(Location location) {
        return generators.add(location);
    }

    public static Location removeGenerator(int index) {
        return generators.remove(index);
    }

    public static void clearGenerators() {
        generators.clear();
    }
}
