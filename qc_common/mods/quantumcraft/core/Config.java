package mods.quantumcraft.core;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import java.io.File;
import java.util.LinkedList;

public class Config {

    protected static File _configFolder;
    protected static File _configFile;


    public static String texturePrefix = "quantumcraft:";
    public static String RIconPrefix = texturePrefix + "research.";

    public static String NameItemPlutoniumIngot = "ingotPlutonium";
    public static String NameItemCrystalQuantonium = "crystalQuantonium";
    public static String NameItemRawQuantonium = "rawQuantonium";
    public static String NameItemDepletedCrystal = "depletedCrystal";
    public static String NameItemResearchBook = "researchBook";
    public static String NameItemMultiTool = "multiTool";

    public static String NameItemQSword = "quantumSword";

    public static String NameItemLocationCard = "locationCard";
    public static String NameItemLocationCardBlank = "locationCardBlank";

    public static String NameOrePlutonium = "orePlutonium";
    public static String NameOreQuantonium = "oreQuantonium";
    public static String NameBlockQDE = "machineQDE";
    public static String NameBlockQEI = "machineQEI";
    public static String NameBlockQDS = "machineQDS";
    public static String NameBlockQES = "machineQES";
    public static String NameBlockQDM = "machineQDM";
    public static String NameBlockQElectrifier = "machineQElectrifier";

    public static String NameBlockQFiberWire = "blockFiberWire";

    public static String NameTextureQOre = "oreQuantonium.ore";
    public static String NameTextureQBase = "oreQuantonium.base";

    public static String NameItemRPlaceHolder = "temp";

    public static LinkedList<String> supports32x = new LinkedList<String>();

    public static final String modNetworkChannel = "QuantumCraft";

    public static Property networkUpdateRange;

    public static Boolean use32x;

    public static int ItemPlutoniumIngotID;
    public static int ItemCrystallizedQuantoniumID;
    public static int ItemRawQuantoniumID;
    public static int ItemDepletedCrystalID;
    public static int ItemResearchBookID;
    public static int ItemMultiToolID;
    public static int ItemLocationCardID;

    public static int ItemQSwordID;

    public static int ItemRPlaceHolderID;

    public static int OrePlutoniumID;
    public static int OreQuantoniumID;
    public static int BlockQDEID;
    public static int BlockQDMID;
    public static int BlockQEIID;
    public static int BlockQDSID;
    public static int BlockQESID;
    public static int BlockQElectrifierID;

    public static int BlockFiberWireID;

    public static String NameRIconTest = RIconPrefix + "test";

    public static String getTextureName(String name) {
        if (use32x) {
            for (String s : supports32x) {
                if (s == name) return texturePrefix + name + ".32";
            }
        }
        return texturePrefix + name;
    }

    public static void initConfig(FMLPreInitializationEvent event) {
        _configFolder = event.getModConfigurationDirectory();
        _configFile = new File(_configFolder.getAbsolutePath() + "/QuantumCraft.cfg");
        add32x();
        loadPropertiesFromFile(_configFile);
    }

    public static void add32x()
    {
        //PUT TEXTURES THAT HAVE 32X VERSION HERE:
        supports32x.add(NameOreQuantonium);
        supports32x.add(NameTextureQOre);
        supports32x.add(NameTextureQBase);
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

        ItemQSwordID = config.get("Items", "ItemQuantumSword", 5006).getInt();

        ItemRPlaceHolderID = config.get("Items", "ItemRPlaceHolder", 4995).getInt();

        OrePlutoniumID = config.get("Blocks", "BlockOrePlutonium", 3499).getInt();
        OreQuantoniumID = config.get("Blocks", "BlockOreQuantonium", 3500).getInt();
        BlockQDEID = config.get("Blocks", "BlockQDeenergizer", 3600).getInt();
        BlockQEIID = config.get("Blocks", "BlockQEInjector", 3601).getInt();
        BlockQDSID = config.get("Blocks", "BlockQDislocator", 3602).getInt();
        BlockQESID = config.get("Blocks", "BlockQESucker", 3603).getInt();
        BlockQDMID = config.get("Blocks", "BlockQDematerializer", 3606).getInt();
        BlockQElectrifierID = config.get("Blocks","BlockQElectrifier",3607).getInt();

        BlockFiberWireID = config.get("Blocks", "BlockQFiberWire", 3604).getInt();

        networkUpdateRange = config.get("general", "Network Update Range", 50.0D);
        networkUpdateRange.comment =
                "This is the distance in which players will be notified.  Lower if you experience lag.";

        use32x = config.get("general", "Use 32x32 textures", true).getBoolean(true);

        config.save();
    }

}
