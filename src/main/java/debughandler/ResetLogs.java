package debughandler;

import java.io.File;
import java.util.ArrayList;

public class ResetLogs {
    public static void resetLogs(ArrayList<LogHandler> handlers) {
        for (LogHandler handler : handlers) {
            File log = new File("debughandler/" + handler.name + ".log");
            System.out.println(log);
            System.out.println(handler.name);
            if (log.delete()){
                handler.debugPrint("Log was reset");
            }else{
                handler.debugPrint("Log could not be reset");
            }
        }
    }
}
