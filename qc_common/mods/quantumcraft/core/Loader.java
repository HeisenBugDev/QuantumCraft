package mods.quantumcraft.core;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mods.quantumcraft.blocks.BlockOreQuantonium;
import mods.quantumcraft.blocks.BlockQDeenergizer;
import mods.quantumcraft.items.ItemLocationCard;
import mods.quantumcraft.items.ItemMultiTool;
import mods.quantumcraft.items.ItemResearchBook;
import mods.quantumcraft.machine.TileQDeenergizer;
import net.minecraft.item.Item;

public class Loader {

    public static Item ItemCrystalQuantonium;
    public static Item ItemRawQuantonium;
    public static Item ItemMultiTool;
    public static ItemResearchBook ItemResearchBook;
    public static Item ItemDepletedCrystal;
    public static ItemLocationCard ItemLocationCard;
    public static BlockOreQuantonium OreQuantonium;
    public static BlockQDeenergizer BlockQDeenergizer;
    public static TabQuantumCraft tabQuantumCraft;

    public static void initAll() {
        initTabs();
        initItems();
        initBlocks();
        initRenderers();
        initWGen();
        initTEs();
        CraftingManager.addCrafting();
        CraftingManager.addSmelting();
        CraftingManager.addQDE();
    }

    public static void initTabs() {
        tabQuantumCraft = new TabQuantumCraft();
    }

    public static void initItems() {
        ItemCrystalQuantonium = new Item(Config.ItemCrystallizedQuantoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemCrystalQuantonium)
                .func_111206_d(Config.NameItemCrystalQuantonium);
        LanguageRegistry.addName(ItemCrystalQuantonium, "Crystallized Quantonium");

        ItemRawQuantonium = new Item(Config.ItemRawQuantoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemRawQuantonium)
                .func_111206_d(Config.NameItemRawQuantonium);
        LanguageRegistry.addName(ItemRawQuantonium, "Raw Quantonium");

        ItemMultiTool = (new ItemMultiTool(Config.ItemMultiToolID)).setUnlocalizedName(Config.NameItemMultiTool)
                .setCreativeTab(tabQuantumCraft)
                .func_111206_d(Config.NameItemMultiTool);
        LanguageRegistry.addName(ItemMultiTool, "Multi Tool");

        ItemDepletedCrystal = new Item(Config.ItemDepletedCrystalID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemDepletedCrystal)
                .func_111206_d(Config.NameItemDepletedCrystal);
        LanguageRegistry.addName(ItemDepletedCrystal, "Depleted Crystal");

        ItemResearchBook = (ItemResearchBook) new ItemResearchBook(Config.ItemResearchBookID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemResearchBook)
                .func_111206_d(Config.NameItemResearchBook);
        LanguageRegistry.addName(ItemResearchBook, "Research Book");

        ItemLocationCard = (ItemLocationCard) new ItemLocationCard(Config.ItemLocationCardID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemLocationCard)
                .func_111206_d(Config.NameItemLocationCard);
        LanguageRegistry.addName(ItemLocationCard, "Location Card");

    }

    public static void initBlocks() {
        OreQuantonium = (BlockOreQuantonium) new BlockOreQuantonium(Config.OreQuantoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameOreQuantonium)
                .func_111022_d(Config.NameOreQuantonium);
        LanguageRegistry.addName(OreQuantonium, "Quantonium Ore");
        GameRegistry.registerBlock(OreQuantonium, Config.NameOreQuantonium);

        BlockQDeenergizer = (BlockQDeenergizer) new BlockQDeenergizer(Config.BlockQDEID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQDE)
                .func_111022_d(Config.NameBlockQDE);
        LanguageRegistry.addName(BlockQDeenergizer, "Quantum De-Energizer");
        GameRegistry.registerBlock(BlockQDeenergizer, Config.NameBlockQDE);
    }

    public static void initRenderers() {

    }

    public static void initWGen() {

    }

    public static void initTEs() {
        GameRegistry.registerTileEntity(TileQDeenergizer.class, "QDeenergizerTE");
    }

}
