package debughandler;

import java.util.ArrayList;

public class DebugRegistry {
    private static ArrayList<LogHandler> logHandlers = new ArrayList<LogHandler>();

    public static void addLogHandler(LogHandler handler){
       logHandlers.add(handler);
    }

    public static void uploadLogs(){
        try {
            FileUploader.upload(logHandlers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resetLogs(){
        ResetLogs.resetLogs(logHandlers);
    }
}
