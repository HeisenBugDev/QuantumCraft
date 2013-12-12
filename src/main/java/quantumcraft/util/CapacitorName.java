package quantumcraft.util;

public class CapacitorName {
    public static String getName(int tier) {
        switch(tier){
            case 1:
                return "Basic";
            case 2:
                return "Advanced";
            case 3:
                return "Super";
            case 4:
                return "Ultra";
            case 5:
                return "Insane";
        }
        return "ERROR! REPORT THIS BUG!";
    }

}
