package mods.quantumcraft.core;

import net.minecraftforge.common.Configuration;

public class Config {

    public static String NameItemCrystalQuantonium = "QuantumCraft:crystalQuantonium";
    public static String NameItemRawQuantonium = "QuantumCraft:rawQuantonium";
    public static String NameItemDepletedCrystal = "QuantumCraft:depletedCrystal";
    public static String NameItemResearchBook = "QuantumCraft:researchBook";
    public static String NameItemMultiTool = "QuantumCraft:multiTool";
    public static String NameItemLocationCard = "QuantumCraft:locationCard";
    
    public static String NameOreQuantonium = "QuantumCraft:oreQuantonium";
    public static String NameBlockQDE = "QuantumCraft:machineQDE";

    public static int ItemCrystallizedQuantoniumID;
    public static int ItemRawQuantoniumID;
    public static int ItemDepletedCrystalID;
    public static int ItemResearchBookID;
    public static int ItemMultiToolID;
    public static int ItemLocationCardID;
    
    public static int OreQuantoniumID;
    public static int BlockQDEID;

    public static void loadConfig(Configuration config) {
        config.load();

        ItemCrystallizedQuantoniumID = config.get("Items", "ItemCrystallizedQuantonium", 5000).getInt();
        ItemRawQuantoniumID = config.get("Items", "ItemRawQuantonium", 5001).getInt();
        ItemDepletedCrystalID = config.get("Items", "ItemDepletedCrystal", 5002).getInt();
        ItemResearchBookID = config.get("Items", "ItemResearchBookID", 5003).getInt();
        ItemMultiToolID = config.get("Items", "ItemMultiTool", 5004).getInt();
        ItemLocationCardID = config.get("Items", "ItemLocationCard", 5005).getInt();

        OreQuantoniumID = config.get("Blocks", "BlockOreQuantonium", 3500).getInt();
        BlockQDEID = config.get("Blocks", "BlockQDeenergizer", 3600).getInt();

        config.save();
    }

}
