package mods.QuantumCraft.core;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import mods.QuantumCraft.items.*;

public class Loader {

	public static String NameItemCrystalQuantonium = "QuantumCraft:crystalQuantonium";
	public static ItemCrystalQuantonium ItemCrystalQuantonium;

	public static TabQuantumCraft tabQuantumCraft;
	
	public static void initAll() {
		initTabs();
		initItems();
		initBlocks();
		initRenderers();
		initWGen();
	}

	public static void initTabs()
	{
		tabQuantumCraft = new TabQuantumCraft();
	}
	
	public static void initItems() {
		ItemCrystalQuantonium = (ItemCrystalQuantonium) new ItemCrystalQuantonium(Config.ItemQuantoniumID)
				.setCreativeTab(tabQuantumCraft).setUnlocalizedName(NameItemCrystalQuantonium);
		LanguageRegistry.addName(ItemCrystalQuantonium, "Crystallized Quantonium");
	}

	public static void initBlocks() {

	}

	public static void initRenderers() {

	}

	public static void initWGen() {

	}

}
