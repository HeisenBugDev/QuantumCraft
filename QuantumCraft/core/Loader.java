package mods.quantumcraft.core;

import mods.quantumcraft.blocks.BlockOreQuantonium;
import mods.quantumcraft.blocks.BlockQDeenergizer;
import mods.quantumcraft.items.ItemBase;
import mods.quantumcraft.items.ItemResearchBook;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Loader {

	public static Item ItemCrystalQuantonium;
	public static Item ItemRawQuantonium;
	public static Item ItemMultiTool;
	public static ItemResearchBook ItemResearchBook;
	
	public static BlockOreQuantonium OreQuantonium;
	public static BlockQDeenergizer BlockQDeenergizer;
	
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
		
		ItemMultiTool = new ItemBase(Config.ItemMultiTool).setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemMultiTool);
		LanguageRegistry.addName(ItemMultiTool, "Multi Tool");
		
		ItemResearchBook = (ItemResearchBook) new ItemResearchBook(Config.ItemResearchBookID)
			.setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemResearchBook);
		LanguageRegistry.addName(ItemResearchBook, "Research Book");
	}

	public static void initBlocks() {
		OreQuantonium = (BlockOreQuantonium) new BlockOreQuantonium(Config.OreQuantoniumID)
			.setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameOreQuantonium);
		LanguageRegistry.addName(OreQuantonium, "Quantonium Ore");
		GameRegistry.registerBlock(OreQuantonium, Config.NameOreQuantonium);
		
		BlockQDeenergizer = (BlockQDeenergizer) new BlockQDeenergizer(Config.BlockQDEID)
			.setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQDE);
		LanguageRegistry.addName(BlockQDeenergizer, "Quantum De-Energizer");
		GameRegistry.registerBlock(BlockQDeenergizer, Config.NameBlockQDE);
	}

	public static void initRenderers() {

	}

	public static void initWGen() {

	}

}
