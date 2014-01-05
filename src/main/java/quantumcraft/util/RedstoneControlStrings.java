package quantumcraft.util;

import net.minecraft.util.EnumChatFormatting;

public class RedstoneControlStrings {
    public static String IGNORE = "redstone ="+ EnumChatFormatting.YELLOW +" don't care";
    public static String RUN_ON_PWR = "redstone ="+ EnumChatFormatting.GREEN +" run";
    public static String STOP_ON_PWR = "redstone ="+ EnumChatFormatting.RED +" stop";
    public static String getByOrdinal(int ordinal) {
        switch (ordinal) {
            case 0:return IGNORE;
            case 1:return RUN_ON_PWR;
            case 2: //FAL THROUGH
            default: return STOP_ON_PWR;
        }
    }
}
