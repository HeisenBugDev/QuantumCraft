package debughandler;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Config {
    protected static File _configFolder;
    protected static File _configFile;
    public static Property debug;
    public static Property debugPrompt;

    public static void initConfig(FMLPreInitializationEvent event){
        _configFolder = event.getModConfigurationDirectory();
        _configFile = new File(_configFolder.getAbsolutePath() + "/DebugHandler.cfg");
        Configuration config = new Configuration(_configFile);
        config.load();
        debug = config.get("general", "Enable debug mode", false);
        debug.comment = ("This is going to be very annoying. Only enable\n" +
                "this if you were asked to or are reporting/fixing a bug. This is \n" +
                "not a \"feature\". We are not Microcrap™ so be warned. Also, by doing\n" +
                "this you are either making a really good or really bad choice. I hope\n" +
                "you know which one it is because I don't");

        debugPrompt = config.get("general", "Be prompted with debug log", true);
        debugPrompt.comment = ("If you have debug enabled and would like to be given a link to the uploaded logs " +
                "AUTOMATICALLY whenever you close the game. This should only be disabled by devs because otherwise it" +
                " is mostly useless having debug enabled");
        config.save();
    }

}
