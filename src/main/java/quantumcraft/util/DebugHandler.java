package quantumcraft.util;

import quantumcraft.core.Config;
import quantumcraft.tile.abstracttiles.TileMachineBase;

public class DebugHandler {
    public static void debugPrint(String str) {
        if (Config.debug.getBoolean(false)) {
            System.out.println("[QuantumCraft][Debug] " + str);
        }
    }

    public static void debugPrint(Object obj) {
        debugPrint(obj.toString());
    }

    public static void debugPrint(TileMachineBase tile, String str) {
        debugPrint(tile.getClass().toString() + " at: [x]" + tile.xCoord + " | [y]" +
                tile.yCoord + " | [z]" +
                tile.zCoord + " => " + str);
    }
}
