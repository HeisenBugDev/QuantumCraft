package mods.QuantumCraft.core;

import net.minecraftforge.common.Configuration;

public class Config {


	public static String NameItemCrystalQuantonium = "QuantumCraft:crystalQuantonium";
	public static String NameItemRawQuantonium = "QuantumCraft:rawQuantonium";
	public static String NameOreQuantonium = "QuantumCraft:oreQuantonium";
	
	public static int ItemCrystallizedQuantoniumID;
	public static int ItemRawQuantoniumID;
	
	public static int OreQuantoniumID;
	
	public static void loadConfig(Configuration config)
	{
		config.load();
		ItemCrystallizedQuantoniumID = config.get("Items", "ItemCrystallizedQuantonium", 5000).getInt();
		ItemRawQuantoniumID = config.get("Items", "ItemRawQuantonium", 5001).getInt();
		OreQuantoniumID = config.get("Blocks", "BlockOreQuantonium", 3500).getInt();
		config.save();
	}
	
}
