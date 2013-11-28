package quantumcraft.util;

import quantumcraft.core.Config;

public class DebugHandler {
    public static void debugPrint(String str){
        if (Config.debug.getBoolean(false)){
            System.out.println("[QuantumCraft][Debug] " + str);
        }
    }
}
