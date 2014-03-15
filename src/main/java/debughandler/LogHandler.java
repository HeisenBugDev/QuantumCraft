package debughandler;

import net.minecraft.tileentity.TileEntity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LogHandler {
    public String name;
    private Date lastDate;

    public LogHandler(String nameSet) {
        name = nameSet;
        lastDate = new Date();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void debugPrint(String str) {
        if (Config.debug == null) return;
        if (!Config.debug.getBoolean(false)) return;
        try {
            File file = new File("debughandler/");
            file.mkdirs();
            file = new File("debughandler/" + name + ".log");
            PrintWriter out = new PrintWriter(new BufferedWriter(new java.io.FileWriter(file, true)));
            String time = "";
            if (TimeUnit.MILLISECONDS.toSeconds(new Date().getTime()) !=
                    TimeUnit.MILLISECONDS.toSeconds(lastDate.getTime())) {
                lastDate = new Date();
                time = lastDate + "\n";
            }
            out.printf(time + "\t[%s][Debug] " + str + "\n", this.name);
            out.close();
        } catch (IOException e) {
            //oh noes!
        }
    }

    public void debugPrint(Object obj) {
        debugPrint(obj.toString());
    }

    public void debugPrint(TileEntity tile, String str) {
        debugPrint(tile.getClass().toString() + " at: [x]" + tile.xCoord + " | [y]" +
                tile.yCoord + " | [z]" +
                tile.zCoord + " => " + str);
    }
}
