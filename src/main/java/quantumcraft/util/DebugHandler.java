package quantumcraft.util;

import org.apache.commons.io.IOUtils;
import quantumcraft.core.Config;
import quantumcraft.tile.abstracttiles.TileMachineBase;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DebugHandler {
    private static Date lastDate = new Date();

    public static void debugPrint(String str) {
        if (Config.debug != null) {
            if (Config.debug.getBoolean(false)) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("QuantumCraft.log", true)));
                    String time = "";
                    if (TimeUnit.MILLISECONDS.toSeconds(new Date().getTime()) !=
                            TimeUnit.MILLISECONDS.toSeconds(lastDate.getTime())) {
                        lastDate = new Date();
                        time = lastDate + "\n";
                    }
                    out.println(time + "\t[QuantumCraft][Debug] " + str);
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

    public static void notifyUser() {
        if (Config.debug.getBoolean(false)) {
            try {
                DebugHandler.postToHastebin();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public static void resetLogs() {
        if (Config.debug.getBoolean(false)) {
            File qcLog = new File("QuantumCraft.log");
            File forgeLog = new File("ForgeModLoader-client-0.log");

            if (qcLog.delete()) {
                debugPrint("QuantumCraft log was reset successfully");
            } else {
                debugPrint("QuantumCraft log could not be deleted");
            }
            if (forgeLog.delete()) {
                debugPrint("FML Log was reset successfully");
            } else {
                debugPrint("FML Log could not be deleted");
            }
        }
    }

    public static void postToHastebin() throws Exception {
        FileInputStream inputStream = new FileInputStream("QuantumCraft.log");
        FileInputStream inputStream1 = new FileInputStream("ForgeModLoader-client-0.log");
        String everything = "########################\n" +
                "### QuantumCraft Log ###\n" +
                "########################\n\n\n";
        try {
            everything += IOUtils.toString(inputStream);
            everything += "\n\n\n########################\n" +
                    "### FML Client Log 0 ###\n" +
                    "########################\n" +
                    "\n" +
                    "\n";
            everything += IOUtils.toString(inputStream1);
        } finally {
            inputStream.close();
            inputStream1.close();
        }

        URL url = new URL("http://hastebin.com/documents");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(everything);
        out.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
            System.out.println(decodedString);
            decodedString = decodedString.replaceAll("\\{", "");
            decodedString = decodedString.replaceAll("}", "");
            decodedString = decodedString.replaceAll("\"", "");
            decodedString = decodedString.replaceAll(":", "");
            decodedString = decodedString.replaceAll("key", "");
            new DebugPrompt(decodedString);
        }
        in.close();
    }
}
