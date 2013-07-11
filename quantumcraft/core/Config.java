package mods.quantumcraft.core;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import java.io.File;

public class Config {

    public static String NameItemLocationCard = "quantumcraft:locationCard";

    protected static File _configFolder;
    protected static File _configFile;

    public static String NameItemCrystalQuantonium = "QuantumCraft:crystalQuantonium";
    public static String NameItemRawQuantonium = "QuantumCraft:rawQuantonium";
    public static String NameItemDepletedCrystal = "QuantumCraft:depletedCrystal";
    public static String NameItemResearchBook = "QuantumCraft:researchBook";
    public static String NameItemMultiTool = "QuantumCraft:multiTool";

    public static String NameOreQuantonium = "QuantumCraft:oreQuantonium";
    public static String NameBlockQDE = "QuantumCraft:machineQDE";

    public static final String modNetworkChannel = "QuantumCraft";

    public static Property networkUpdateRange;

    public static int ItemCrystallizedQuantoniumID;
    public static int ItemRawQuantoniumID;
    public static int ItemDepletedCrystalID;
    public static int ItemResearchBookID;
    public static int ItemMultiToolID;
    public static int ItemLocationCardID;
    
    public static int OreQuantoniumID;
    public static int BlockQDEID;


    public static void initConfig(FMLPreInitializationEvent event) {
        _configFolder = event.getModConfigurationDirectory();
        _configFile = new File(_configFolder.getAbsolutePath() + "/ProjectRed.cfg");
        loadPropertiesFromFile(_configFile);
    }


    public static void loadPropertiesFromFile(File file) {
        Configuration config = new Configuration(file);
        config.load();

        ItemCrystallizedQuantoniumID = config.get("Items", "ItemCrystallizedQuantonium", 5000).getInt();
        ItemRawQuantoniumID = config.get("Items", "ItemRawQuantonium", 5001).getInt();
        ItemDepletedCrystalID = config.get("Items", "ItemDepletedCrystal", 5002).getInt();
        ItemResearchBookID = config.get("Items", "ItemResearchBookID", 5003).getInt();
        ItemMultiToolID = config.get("Items", "ItemMultiTool", 5004).getInt();
        ItemLocationCardID = config.get("Items", "ItemLocationCard", 5005).getInt();

        OreQuantoniumID = config.get("Blocks", "BlockOreQuantonium", 3500).getInt();
        BlockQDEID = config.get("Blocks", "BlockQDeenergizer", 3600).getInt();


        networkUpdateRange = config.get("general", "Network Update Range", 50.0D);
        networkUpdateRange.comment = "This is the distance in which players will be notified.  Lower if you experience lag.";

        config.save();
    }

}
