package quantumcraft.core;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import java.io.File;
import java.util.LinkedList;

public class Config {

    public static final String modNetworkChannel = "QuantumCraft";
    public static String texturePrefix = "quantumcraft:";
    public static String RIconPrefix = texturePrefix + "research.";
    public static String NameItemUnbioxeniumIngot = "ingotUnbioxenium";
    public static String NameItemCrystalQuantonium = "crystalQuantonium";
    public static String NameItemRawQuantonium = "rawQuantonium";
    public static String NameItemDepletedCrystal = "depletedCrystal";
    public static String NameItemResearchBook = "researchBook";
    public static String NameItemMultiTool = "multiTool";
    public static String NameItemPortableQCapacitor = "portQCapacitor";
    public static String NameItemUpgrade_ = "upgradeNONE";
    public static String[] NameItemUpgrade =
            {"upgradeTemplate", "upgradeOverclock"};
    public static String NameItemQSword = "quantumSword";
    public static String NameItemQAxe = "quantumAxe";
    public static String NameItemQPick = "quantumPick";
    public static String NameItemQShovel = "quantumShovel";
    public static String NameItemLocationCard = "locationCard";
    public static String NameItemLocationCardBlank = "locationCardBlank";
    public static String NameItemHyperConductor = "hyperConductor";
    public static String NameItemQuantumPlating = "quantumPlating";
    public static String NameOreUnbioxenium = "oreUnbioxenium";
    public static String NameOreQuantonium = "oreQuantonium";
    public static String NameBlockQDE = "machineQDE";
    public static String NameBlockQEI = "machineQEI";
    public static String NameBlockQDS = "machineQDS";
    public static String NameBlockQES = "machineQES";
    public static String NameBlockQDM = "machineQDM";
    public static String NameBlockQEL = "machineQEL";
    public static String NameBlockQEE = "machineQEE";
    public static String NameBlockQDL = "machineQDL";
    public static String NameBlockIOF = "machineIOF";  // Block ION Forge
    public static String NameBlockIOT = "machineIOT";  // Block ION Tunneler
    public static String NameBlockIOS = "machineIOS";  // Block ION Scanner
    public static String NameBlockIOH = "machineIOH";  // Block ION Harvester
    public static String NameBlockQCP = "machineQCP";  // Block Q Capacitor
    public static String NameBlockQFiberWire = "blockFiberWire";
    public static String NameBlockMCasing = "quantumMachineCasing";
    public static String NameTextureQOre = "oreQuantonium.ore";
    public static String NameTextureQBase = "oreQuantonium.base";
    public static String NameItemRPlaceHolder = "temp";
    public static LinkedList<String> supports32x = new LinkedList<String>();
    public static Property networkUpdateRange;
    public static Property beta;
    public static Property debug;
    public static Boolean use32x;
    public static int ItemUnbioxeniumIngotID;
    public static int ItemCrystallizedQuantoniumID;
    public static int ItemRawQuantoniumID;
    public static int ItemDepletedCrystalID;
    public static int ItemResearchBookID;
    public static int ItemMultiToolID;
    public static int ItemLocationCardID;
    public static int ItemLocationdCardBlankID;
    public static int ItemPortQCapacitorID;
    public static int ItemUpgradeID;
    public static int ItemQSwordID;
    public static int ItemQAxeID;
    public static int ItemQPickID;
    public static int ItemQShovelID;
    public static int ItemRPlaceHolderID;
    public static int ItemHyperConductorID;
    public static int ItemQuantumPlatingID;
    public static int OreUnbioxeniumID;
    public static int OreQuantoniumID;
    public static int BlockQDEID;
    public static int BlockQDMID;
    public static int BlockQEIID;
    public static int BlockQDSID;
    public static int BlockQESID;
    public static int BlockQELID;
    public static int BlockQEEID;
    public static int BlockQDLID;
    public static int BlockIOFID;
    public static int BlockIOTID;
    public static int BlockIOSID;
    public static int BlockIOHID;
    public static int BlockQCPID;
    public static int BlockFiberWireID;
    public static int BlockMCasingID;
    public static int IONForgeEnergyCost;
    public static String NameRIconTest = RIconPrefix + "test";
    protected static File _configFolder;
    protected static File _configFile;

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
        _configFile =
                new File(_configFolder.getAbsolutePath() + "/QuantumCraft.cfg");
        add32x();
        loadPropertiesFromFile(_configFile);
    }

    public static void add32x() {
        //PUT TEXTURES THAT HAVE 32X VERSION HERE:
        supports32x.add(NameOreQuantonium);
        supports32x.add(NameTextureQOre);
        supports32x.add(NameTextureQBase);
    }

    public static void loadPropertiesFromFile(File file) {
        Configuration config = new Configuration(file);
        config.load();

        ItemUnbioxeniumIngotID =
                config.get("Items", "ItemIngotUnbioxenium", 4999).getInt();
        ItemCrystallizedQuantoniumID =
                config.get("Items", "ItemCrystallizedQuantonium", 5000)
                        .getInt();
        ItemRawQuantoniumID =
                config.get("Items", "ItemRawQuantonium", 5001).getInt();
        ItemDepletedCrystalID =
                config.get("Items", "ItemDepletedCrystal", 5002).getInt();
        ItemResearchBookID =
                config.get("Items", "ItemResearchBookID", 5003).getInt();
        ItemMultiToolID = config.get("Items", "ItemMultiTool", 5004).getInt();
        ItemLocationdCardBlankID =
                config.get("Items", "ItemLocationCardBlank", 5005).getInt();
        ItemLocationCardID =
                config.get("Items", "ItemLocationCard", 5006).getInt();

        ItemPortQCapacitorID =
                config.get("Items", "ItemPortableQuantumCapacitor", 5007)
                        .getInt();
        ItemUpgradeID = config.get("Items", "ItemUpgrade", 5008).getInt();

        ItemQSwordID = config.get("Items", "ItemQuantumSword", 5020).getInt();
        ItemQAxeID = config.get("Items", "ItemQuantumAxe", 5021).getInt();
        ItemQPickID = config.get("Items", "ItemQuantumPick", 5022).getInt();
        ItemQShovelID = config.get("Items", "ItemQuantumShovel", 5023).getInt();

        ItemRPlaceHolderID =
                config.get("Items", "ItemRPlaceHolder", 4995).getInt();

        ItemHyperConductorID =
                config.get("Items", "ItemHyperConductor", 5009).getInt();
        ItemQuantumPlatingID =
                config.get("Items", "ItemQuantumPlating", 5010).getInt();

        OreUnbioxeniumID =
                config.get("Blocks", "BlockOreUnbioxenium", 3499).getInt();
        OreQuantoniumID =
                config.get("Blocks", "BlockOreQuantonium", 3500).getInt();
        BlockQDEID = config.get("Blocks", "BlockQDeenergizer", 3600).getInt();
        BlockQEIID = config.get("Blocks", "BlockQEInjector", 3601).getInt();
        BlockQDSID = config.get("Blocks", "BlockQDislocator", 3602).getInt();
        BlockQESID = config.get("Blocks", "BlockQESucker", 3603).getInt();
        BlockQDMID =
                config.get("Blocks", "BlockQDematerializer", 3606).getInt();
        BlockQELID = config.get("Blocks", "BlockQElectrifier", 3607).getInt();
        BlockQEEID = config.get("Blocks", "BlockQEExtractor", 3608).getInt();
        BlockQDLID = config.get("Blocks", "BlockQDeelectrifier", 3609).getInt();
        BlockIOFID = config.get("Blocks", "BlockIONForge", 3610).getInt();
        BlockIOTID = config.get("Blocks", "BlockIONTunneler", 3611).getInt();
        BlockIOSID = config.get("Blocks", "BlockIONScanner", 3612).getInt();
        BlockIOHID = config.get("Blocks", "BlockIONHarvester", 3613).getInt();
        BlockQCPID = config.get("Blocks", "BlockQCapacitor", 3614).getInt();

        BlockFiberWireID =
                config.get("Blocks", "BlockQFiberWire", 3604).getInt();
        BlockMCasingID =
                config.get("Blocks", "BlockMachineCasing", 3590).getInt();

        IONForgeEnergyCost =
                config.get("Units", "ION Forge Energy Cost", 3).getInt();

        networkUpdateRange =
                config.get("general", "Network Update Range", 50.0D);
        networkUpdateRange.comment =
                "This is the distance in which players will be notified.  Lower if you experience lag.";

        use32x = config.get("general", "Use 32x32 textures", true)
                .getBoolean(true);
        beta = config.get("general", "Enable beta features", false);
        beta.comment =
                ("(WARNING! This could corrupt your world or break your game)");

        debug = config.get("general", "Enable debug mode", false);
        debug.comment =
                ("This is going to be very annoying if you have a console open. Only enable\n" +
                        "this if you were asked to or are reporting/fixing a bug. This is \n" +
                        "not a\"feature\". We are not Microcrap™ so be warned. Also, by doing\n" +
                        "this you are either making a really good or really bad choice. I hope\n" +
                        "you know which one it is because I don't")
        ;
        config.save();
    }

}
