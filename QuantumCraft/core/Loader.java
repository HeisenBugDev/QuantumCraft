package mods.QuantumCraft.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import mods.QuantumCraft.items.*;

public class Loader {

	public static Item ItemCrystalQuantonium;
	public static Item ItemRawQuantonium;
	
	public static BlockOre OreQuantonium;
	
	public static TabQuantumCraft tabQuantumCraft;
	
	public static void initAll() {
		initTabs();
		initItems();
		initBlocks();
		initRenderers();
		initWGen();
		CraftingManager.addCrafting();
		CraftingManager.addSmelting();
	}

	public static void initTabs()
	{
		tabQuantumCraft = new TabQuantumCraft();
	}
	
	public static void initItems() {
		ItemCrystalQuantonium = new Item(Config.ItemCrystallizedQuantoniumID)
				.setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemCrystalQuantonium);
		LanguageRegistry.addName(ItemCrystalQuantonium, "Crystallized Quantonium");
		
		ItemRawQuantonium = new Item(Config.ItemRawQuantoniumID)
			.setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemRawQuantonium);
		LanguageRegistry.addName(ItemRawQuantonium, "Raw Quantonium");
	}

	public static void initBlocks() {
		OreQuantonium = (BlockOre) new BlockOre(Config.OreQuantoniumID)
			.setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameOreQuantonium);
		LanguageRegistry.addName(OreQuantonium, "Quantonium Ore");
		GameRegistry.registerBlock(OreQuantonium, Config.NameOreQuantonium);
	}

	public static void initRenderers() {

	}

	public static void initWGen() {

	}

}
