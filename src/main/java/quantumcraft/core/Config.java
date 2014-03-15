package quantumcraft.core;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.LinkedList;

public class Config {

    public static final String modNetworkChannel = "quantumcraft";
    public static String texturePrefix = "QuantumCraft:";
    public static String NameItemInfinitePower = "item_infinite_power";
    public static String NameItemUnbioxeniumIngot = "ingot_unbioxenium";
    public static String NameItemCrystalQuantonium = "crystal_quantonium";
    public static String NameItemRawQuantonium = "raw_quantonium";
    public static String NameItemDepletedCrystal = "depleted_crystal";
    public static String NameItemMultiTool = "multi_tool";
    public static String NameItemPortableQCapacitor = "portable_quantum_capacitor";
    public static String NameItemUpgrade_ = "upgrade_NONE";
    public static String[] NameItemUpgrade = {"upgrade_template", "upgrade_overclock"};
    public static String NameItemQuantumSword = "quantum_sword";
    public static String NameItemQuantumAxe = "quantum_axe";
    public static String NameItemQuantumPick = "quantum_pick";
    public static String NameItemQuantumShovel = "quantum_shovel";
    public static String NameItemLocationCard = "location_card";
    public static String NameItemLocationCardBlank = "location_card_blank";
    public static String NameItemHyperConductor = "hyper_conductor";
    public static String NameItemQuantumPlating = "quantum_plating";
    public static String NameItemQuantumConverter = "quantum_converter";
    public static String NameOreUnbioxenium = "ore_unbioxenium";
    public static String NameOreQuantonium = "ore_quantonium";
    public static String NameBlockQuantumDeenergizer = "machine_quantum_deenergizer";
    public static String NameBlockQuantumEnergyInjector = "machine_quantum_energy_injector";
    public static String NameBlockQuantumEnergySucker = "machine_quantum_energy_sucker";
    public static String NameBlockQuantumDematerializer = "machine_quantum_dematerializer";
    public static String NameBlockQuantumEnergyExtractor = "machine_quantum_energy_extractor";
    public static String NameBlockIONForge = "machine_ion_forge";
    public static String NameBlockIONTunneler = "machine_ion_tunneler";
    public static String NameBlockIONScanner = "machine_ion_scanner";
    public static String NameBlockIONHarvester = "machine_ion_harvester";
    public static String NameBlockQuantumCapacitor = "machine_quantum_capacitor";
    public static String NameBlockQuantumInterdimensionalGenerator = "machine_quantum_interdimensional_generator";
    public static String NameBlockQuantumFibreWire = "block_fiber_wire";
    public static String NameBlockMachineCasing = "quantum_machine_casing";
    public static String NameTextureQuantumOre = "ore_quantonium.ore";
    public static String NameTextureQuantumOreBase = "ore_quantonium.base";
    public static String NameTextureQuantumOreFull = "ore_quantonium";
    public static LinkedList<String> supports32x = new LinkedList<String>();
    public static Property networkUpdateRange;
    public static Property beta;
    public static Boolean use32x;
    public static int IONForgeEnergyCost;
    protected static File _configFolder;
    protected static File _configFile;

    public static String getTextureName(String name) {
        if (use32x) {
            for (String s : supports32x) {
                if (s.equals(name)) return texturePrefix + name + ".32";
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

    public static void add32x() {
        //PUT TEXTURES THAT HAVE 32X VERSION HERE:
        supports32x.add(NameOreQuantonium);
        supports32x.add(NameTextureQuantumOre);
        supports32x.add(NameTextureQuantumOreBase);
    }

    public static void loadPropertiesFromFile(File file) {
        Configuration config = new Configuration(file);
        config.load();

        IONForgeEnergyCost = config.get("Units", "ION Forge Energy Cost", 128).getInt();

        networkUpdateRange = config.get("general", "Network Update Range", 50.0D);
        networkUpdateRange.comment =
                "This is the distance in which players will be notified.  Lower if you experience lag.";

        use32x = config.get("general", "Use 32x32 assets.QuantumCraft.textures", true).getBoolean(true);
        beta = config.get("general", "Enable beta features", false);
        beta.comment = ("(WARNING! This could corrupt your world or break your game)");

        config.save();
    }

}
