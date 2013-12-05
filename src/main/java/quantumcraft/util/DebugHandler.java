package quantumcraft.util;

import quantumcraft.core.Config;
import quantumcraft.tile.abstracttiles.TileMachineBase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DebugHandler {
    public static void debugPrint(String str) {
        if (Config.debug != null) {
            if (Config.debug.getBoolean(false)) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("QuantumCraft.log", true)));
                    out.println("[QuantumCraft][Debug] " + str);
                    out.close();
                } catch (IOException e) {
                    //oh noes!
                }
            }
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
