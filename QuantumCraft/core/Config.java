package mods.quantumcraft.core;

import net.minecraftforge.common.Configuration;

public class Config {

	public static String NameItemCrystalQuantonium = "QuantumCraft:crystalQuantonium";
	public static String NameItemRawQuantonium = "QuantumCraft:rawQuantonium";
	public static String NameItemDepletedCrystal = "QuantumCraft:depletedCrystal";
	public static String NameItemResearchBook = "QuantumCraft:researchBook";
	public static String NameItemMultiTool = "QuantumCraft:multiTool";

	public static String NameOreQuantonium = "QuantumCraft:oreQuantonium";
	public static String NameBlockQDE = "QuantumCraft:machineQDE";

	public static int ItemCrystallizedQuantoniumID;
	public static int ItemRawQuantoniumID;
	public static int ItemDepletedCrystalID;
	public static int ItemResearchBookID;
	public static int ItemMultiTool;

	public static int OreQuantoniumID;
	public static int BlockQDEID;

	public static void loadConfig(Configuration config) {
		config.load();

		ItemCrystallizedQuantoniumID = config.get("Items",
				"ItemCrystallizedQuantonium", 5000).getInt();
		ItemRawQuantoniumID = config.get("Items", "ItemRawQuantonium", 5001)
				.getInt();
		ItemResearchBookID = config.get("Items", "ItemResearchBookID", 5002)
				.getInt();
		ItemMultiTool = config.get("Items", "ItemMultiTool", 5003).getInt();
		ItemDepletedCrystalID = config
				.get("Items", "ItemDepletedCrystal", 5004).getInt();
		OreQuantoniumID = config.get("Blocks", "BlockOreQuantonium", 3500)
				.getInt();

		BlockQDEID = config.get("Blocks", "BlockQDeenergizer", 2501).getInt();

		config.save();
	}

}
