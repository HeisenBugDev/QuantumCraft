package mods.quantumcraft.core;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mods.quantumcraft.blocks.*;
import mods.quantumcraft.items.*;
import mods.quantumcraft.machine.*;
import mods.quantumcraft.render.RenderOre;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.util.Icon;
import net.minecraftforge.common.EnumHelper;

public class Loader {

    /* ITEMS */
    public static ItemBase ItemIngotPlutonium;
    public static ItemBase ItemCrystalQuantonium;
    public static ItemBase ItemRawQuantonium;
    public static ItemMultiTool ItemMultiTool;
    public static ItemResearchBook ItemResearchBook;
    public static ItemBase ItemDepletedCrystal;
    public static ItemLocationCard ItemLocationCard;
    public static ItemLocationCardBlank ItemLocationCardBlank;
    public static ItemPortableQCapacitor ItemPortableQCapacitor;

    public static mods.quantumcraft.items.tools.ItemQuantumSword ItemQuantumSword;
    public static mods.quantumcraft.items.tools.ItemQuantumAxe ItemQuantumAxe;
    public static mods.quantumcraft.items.tools.ItemQuantumPick ItemQuantumPick;
    public static mods.quantumcraft.items.tools.ItemQuantumShovel ItemQuantumShovel;

    public static ItemPlaceholder ItemRPlaceHolder;
    /* BLOCKS */
    public static BlockOre OrePlutonium;
    public static BlockOreQuantonium OreQuantonium;
    /* MACHINE BLOCKS */
    public static BlockQDeenergizer BlockQDeenergizer;
    public static BlockQEInjector BlockQEInjector;
    public static BlockQDislocator BlockQDislocator;
    public static BlockQEnergySucker BlockQEnergySucker;
    public static BlockQDematerializer BlockQDematerializer;
    public static BlockQElectrifier BlockQElectrifier;
    public static BlockQEExtractor BlockQEExtractor;
    /* OTHER BLOCKS */
    public static BlockQuantumFiberWire BlockQuantumFiberWire;
    /* CREATIVE TABS */
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
        ItemIngotPlutonium = (ItemBase) new ItemBase(Config.ItemPlutoniumIngotID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemPlutoniumIngot)
                .func_111206_d(Config.getTextureName(Config.NameItemPlutoniumIngot));
        LanguageRegistry.addName(ItemIngotPlutonium, "Plutonium Ingot");

        ItemCrystalQuantonium = (ItemBase) new ItemBase(Config.ItemCrystallizedQuantoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemCrystalQuantonium)
                .func_111206_d(Config.getTextureName(Config.NameItemCrystalQuantonium));
        LanguageRegistry.addName(ItemCrystalQuantonium, "Crystallized Quantonium");

        ItemRawQuantonium = (ItemBase) new ItemBase(Config.ItemRawQuantoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemRawQuantonium)
                .func_111206_d(Config.getTextureName(Config.NameItemRawQuantonium));
        LanguageRegistry.addName(ItemRawQuantonium, "Raw Quantonium");

        ItemMultiTool = (ItemMultiTool) new ItemMultiTool(Config.ItemMultiToolID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemMultiTool)
                .func_111206_d(Config.getTextureName(Config.NameItemMultiTool));
        LanguageRegistry.addName(ItemMultiTool, "Quantum Multi Tool");

        ItemDepletedCrystal = (ItemBase) new ItemBase(Config.ItemDepletedCrystalID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemDepletedCrystal)
                .func_111206_d(Config.getTextureName(Config.NameItemDepletedCrystal));
        LanguageRegistry.addName(ItemDepletedCrystal, "Depleted Crystal");

        ItemResearchBook = (ItemResearchBook) new ItemResearchBook(Config.ItemResearchBookID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemResearchBook)
                .func_111206_d(Config.getTextureName(Config.NameItemResearchBook));
        LanguageRegistry.addName(ItemResearchBook, "Research Book");

        ItemLocationCard = (ItemLocationCard) new ItemLocationCard(Config.ItemLocationCardID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemLocationCard)
                .func_111206_d(Config.getTextureName(Config.NameItemLocationCard));
        LanguageRegistry.addName(ItemLocationCard, "Location Card");

        ItemLocationCardBlank = (ItemLocationCardBlank) new ItemLocationCardBlank(Config.ItemLocationdCardBlankID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemLocationCardBlank)
                .func_111206_d(Config.getTextureName(Config.NameItemLocationCardBlank));
        LanguageRegistry.addName(ItemLocationCardBlank, "Location Card");

        ItemPortableQCapacitor = (ItemPortableQCapacitor) new ItemPortableQCapacitor(Config.ItemPortQCapacitorID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemPortableQCapacitor)
                .func_111206_d(Config.getTextureName(Config.NameItemPortableQCapacitor));
        LanguageRegistry.addName(ItemPortableQCapacitor, "Portable Quantum Capacitor");
        /*
        ItemQuantumSword = (ItemQuantumSword) new ItemQuantumSword(Config.ItemQSwordID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemQSword)
                .func_111206_d(Config.getTextureName(Config.NameItemQSword));
        LanguageRegistry.addName(ItemQuantumSword, "Quantum Sword");

        ItemQuantumAxe = (ItemQuantumAxe) new ItemQuantumAxe(Config.ItemQAxeID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemQAxe)
                .func_111206_d(Config.getTextureName(Config.NameItemQAxe));
        LanguageRegistry.addName(ItemQuantumAxe, "Quantum Axe");

        ItemQuantumPick = (ItemQuantumPick) new ItemQuantumPick(Config.ItemQPickID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemQPick)
                .func_111206_d(Config.getTextureName(Config.NameItemQPick));
        LanguageRegistry.addName(ItemQuantumPick, "Quantum Pickaxe");

        ItemQuantumShovel = (ItemQuantumShovel) new ItemQuantumShovel(Config.ItemQShovelID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemQShovel)
                .func_111206_d(Config.getTextureName(Config.NameItemQShovel));
        LanguageRegistry.addName(ItemQuantumShovel, "Quantum Shovel");
        */
        ItemRPlaceHolder = (ItemPlaceholder) new ItemPlaceholder(Config.ItemRPlaceHolderID)
                .setCreativeTab(null).setUnlocalizedName(Config.NameItemRPlaceHolder)
                .func_111206_d(Config.getTextureName(Config.NameItemRPlaceHolder));
        LanguageRegistry.addName(ItemRPlaceHolder, "Render Placeholder");
    }

    public static void initBlocks() {
        OrePlutonium = (BlockOre) new BlockOre(Config.OrePlutoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameOrePlutonium)
                .func_111022_d(Config.getTextureName(Config.NameOrePlutonium)).setHardness(2.5F);
        LanguageRegistry.addName(OrePlutonium, "Plutonium Ore");
        GameRegistry.registerBlock(OrePlutonium, Config.NameOrePlutonium);

        OreQuantonium = (BlockOreQuantonium) new BlockOreQuantonium(Config.OreQuantoniumID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameOreQuantonium)
                .func_111022_d(Config.getTextureName(Config.NameOreQuantonium));
        LanguageRegistry.addName(OreQuantonium, "Quantonium Ore");
        GameRegistry.registerBlock(OreQuantonium, Config.NameOreQuantonium);

        BlockQDeenergizer = (BlockQDeenergizer) new BlockQDeenergizer(Config.BlockQDEID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQDE)
                .func_111022_d(Config.getTextureName(Config.NameBlockQDE));
        LanguageRegistry.addName(BlockQDeenergizer, "Quantum De-Energizer");
        GameRegistry.registerBlock(BlockQDeenergizer, Config.NameBlockQDE);

        BlockQDematerializer =
                (BlockQDematerializer) new BlockQDematerializer(Config.BlockQDMID).setCreativeTab(tabQuantumCraft)
                        .setUnlocalizedName(Config.NameBlockQDM)
                        .func_111022_d(Config.getTextureName(Config.NameBlockQDM));
        LanguageRegistry.addName(BlockQDematerializer, "Quantum De-Materializer");
        GameRegistry.registerBlock(BlockQDematerializer, Config.NameBlockQDM);

        BlockQEInjector = (BlockQEInjector) new BlockQEInjector(Config.BlockQEIID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQEI)
                .func_111022_d(Config.getTextureName(Config.NameBlockQEI));
        LanguageRegistry.addName(BlockQEInjector, "Quantum Energy Injector");
        GameRegistry.registerBlock(BlockQEInjector, Config.NameBlockQEI);

        BlockQDislocator = (BlockQDislocator) new BlockQDislocator(Config.BlockQDSID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQDS)
                .func_111022_d(Config.getTextureName(Config.NameBlockQDS));
        LanguageRegistry.addName(BlockQDislocator, "Quantum Dislocator");
        GameRegistry.registerBlock(BlockQDislocator, Config.NameBlockQDS);

        BlockQEnergySucker = (BlockQEnergySucker) new BlockQEnergySucker(Config.BlockQESID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQES)
                .func_111022_d(Config.getTextureName(Config.NameBlockQES));
        LanguageRegistry.addName(BlockQEnergySucker, "Quantum Energy Sucker");
        GameRegistry.registerBlock(BlockQEnergySucker, Config.NameBlockQES);

        BlockQuantumFiberWire = (BlockQuantumFiberWire) new BlockQuantumFiberWire(Config.BlockFiberWireID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQFiberWire)
                .func_111022_d(Config.getTextureName(Config.NameBlockQFiberWire));
        LanguageRegistry.addName(BlockQuantumFiberWire, "Quantum Fiber-Wire");
        GameRegistry.registerBlock(BlockQuantumFiberWire, Config.NameBlockQFiberWire);

        BlockQElectrifier = (BlockQElectrifier) new BlockQElectrifier(Config.BlockQElectrifierID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQElectrifier)
                .func_111022_d(Config.getTextureName(Config.NameBlockQElectrifier));
        LanguageRegistry.addName(BlockQElectrifier, "Quantum Electrifier");
        GameRegistry.registerBlock(BlockQElectrifier, Config.NameBlockQElectrifier);

        BlockQEExtractor = (BlockQEExtractor) new BlockQEExtractor(Config.BlockQEEID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQEE)
                .func_111022_d(Config.getTextureName(Config.NameBlockQEE));
        LanguageRegistry.addName(BlockQEExtractor, "Quantum Energy Extractor");
        GameRegistry.registerBlock(BlockQEExtractor, Config.NameBlockQEE);

    }

    public static void initRenderers() {
        RenderingRegistry.registerBlockHandler(RenderOre.instance().getRenderId(), RenderOre.instance());
    }

    public static void initWGen() {

    }

    public static void initTEs() {
        GameRegistry.registerTileEntity(TileQDeenergizer.class, "QDeenergizerTE");
        GameRegistry.registerTileEntity(TileQEInjector.class, "QEInjectorTE");
        GameRegistry.registerTileEntity(TileQDislocator.class, "QDislocatorTE");
        GameRegistry.registerTileEntity(TileQEnergySucker.class, "QESuckerTE");
        GameRegistry.registerTileEntity(TileQDematerializer.class, "QDematerializerTE");
        GameRegistry.registerTileEntity(TileQElectrifier.class, "QElectrifierTE");
        GameRegistry.registerTileEntity(TileQEExtractor.class, "QEExtractorTE");
    }

    public static class ToolMaterials {
        //The following values are subject to change.
        public static EnumToolMaterial QUANTUMTOOL = EnumHelper.addToolMaterial("QUANTUMTOOL", 3, 0, 7.0F, 2, 30);
        //the 3rd argument (0) is not used...
    }

    public static class IconLoader {

        public static Icon oreQuantonium_ore;
        public static Icon oreQuantonium_base;

        public static void loadAll(IconRegister i) {
            System.out.println(Config.getTextureName(Config.NameTextureQOre));
            oreQuantonium_ore = i.registerIcon(Config.getTextureName(Config.NameTextureQOre));
            System.out.println(Config.getTextureName(Config.NameTextureQBase));
            oreQuantonium_base = i.registerIcon(Config.getTextureName(Config.NameTextureQBase));
        }
    }

}
