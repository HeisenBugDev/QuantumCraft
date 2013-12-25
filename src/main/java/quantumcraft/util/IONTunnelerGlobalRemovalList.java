package quantumcraft.util;

import java.util.ArrayList;

public class IONTunnelerGlobalRemovalList {
    public static ArrayList<Coords> blocks = new ArrayList<Coords>();

    public static ArrayList<Coords> getBlocks() {
        return blocks;
    }

    public static void setBlocks(ArrayList<Coords> blocks) {
        IONTunnelerGlobalRemovalList.blocks = blocks;
    }
}
