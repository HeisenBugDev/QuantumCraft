package mods.quantumcraft.core;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import java.io.File;

public class Config {

    protected static File _configFolder;
    protected static File _configFile;

    public static String NameItemPlutoniumIngot = "quantumcraft:ingotPlutonium";
    public static String NameItemCrystalQuantonium = "quantumcraft:crystalQuantonium";
    public static String NameItemRawQuantonium = "quantumcraft:rawQuantonium";
    public static String NameItemDepletedCrystal = "quantumcraft:depletedCrystal";
    public static String NameItemResearchBook = "quantumcraft:researchBook";
    public static String NameItemMultiTool = "quantumcraft:multiTool";

    public static String NameItemLocationCard = "quantumcraft:locationCard";
    public static String NameItemLocationCardBlank = "quantumcraft:locationCardBlank";

    public static String NameOrePlutonium = "quantumcraft:orePlutonium";
    public static String NameOreQuantonium = "quantumcraft:oreQuantonium";
    public static String NameBlockQDE = "quantumcraft:machineQDE";
    public static String NameBlockQEI = "quantumcraft:machineQEI";

    public static String NameItemRPlaceHolder = "quantumcraft:temp";

    public static final String modNetworkChannel = "QuantumCraft";

    public static Property networkUpdateRange;

    public static int ItemPlutoniumIngotID;
    public static int ItemCrystallizedQuantoniumID;
    public static int ItemRawQuantoniumID;
    public static int ItemDepletedCrystalID;
    public static int ItemResearchBookID;
    public static int ItemMultiToolID;
    public static int ItemLocationCardID;

    public static int ItemRPlaceHolderID;

    public static int OrePlutoniumID;
    public static int OreQuantoniumID;
    public static int BlockQDEID;
    public static int BlockQEIID;

    public static String RIconPrefix = "quantumcraft:research.";
    public static String NameRIconTest = RIconPrefix + "test";

    public static void initConfig(FMLPreInitializationEvent event) {
        _configFolder = event.getModConfigurationDirectory();
        _configFile = new File(_configFolder.getAbsolutePath() + "/QuantumCraft.cfg");
        loadPropertiesFromFile(_configFile);
    }

    public static void loadPropertiesFromFile(File file) {
        Configuration config = new Configuration(file);
        config.load();

        ItemPlutoniumIngotID = config.get("Items", "ItemIngotPlutonium", 4999).getInt();
        ItemCrystallizedQuantoniumID = config.get("Items", "ItemCrystallizedQuantonium", 5000).getInt();
        ItemRawQuantoniumID = config.get("Items", "ItemRawQuantonium", 5001).getInt();
        ItemDepletedCrystalID = config.get("Items", "ItemDepletedCrystal", 5002).getInt();
        ItemResearchBookID = config.get("Items", "ItemResearchBookID", 5003).getInt();
        ItemMultiToolID = config.get("Items", "ItemMultiTool", 5004).getInt();
        ItemLocationCardID = config.get("Items", "ItemLocationCard", 5005).getInt();

        ItemRPlaceHolderID = config.get("Items", "ItemRPlaceHolder", 4995).getInt();

        OrePlutoniumID = config.get("Blocks", "BlockOrePlutonium", 3499).getInt();
        OreQuantoniumID = config.get("Blocks", "BlockOreQuantonium", 3500).getInt();
        BlockQDEID = config.get("Blocks", "BlockQDeenergizer", 3600).getInt();
        BlockQEIID = config.get("Blocks", "BlockQEInjector", 3601).getInt();

        networkUpdateRange = config.get("general", "Network Update Range", 50.0D);
        networkUpdateRange.comment =
                "This is the distance in which players will be notified.  Lower if you experience lag.";

        config.save();
    }

}
