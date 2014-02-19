package quantumcraft.core;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import quantumcraft.blocks.*;
import quantumcraft.items.*;
import quantumcraft.items.tools.ItemQuantumAxe;
import quantumcraft.items.tools.ItemQuantumPick;
import quantumcraft.items.tools.ItemQuantumShovel;
import quantumcraft.items.tools.ItemQuantumSword;
import quantumcraft.render.RenderFiberWire;
import quantumcraft.render.RenderOre;
import quantumcraft.tile.*;
import quantumcraft.util.CapacitorName;

public class Loader {

    /* ITEMS */
    public static ItemBase ItemIngotUnbioxenium;
    public static ItemBase ItemCrystalQuantonium;
    public static ItemBase ItemRawQuantonium;
    public static ItemMultiTool ItemMultiTool;
    public static ItemResearchBook ItemResearchBook;
    public static ItemBase ItemDepletedCrystal;
    public static ItemLocationCard ItemLocationCard;
    public static ItemLocationCardBlank ItemLocationCardBlank;
    public static ItemPortableQCapacitor ItemPortableQCapacitor;
    public static ItemInfinitePower ItemInfinitePower;
    public static ItemUpgrade ItemUpgrade;
    public static ItemQuantumSword ItemQuantumSword;
    public static ItemQuantumAxe ItemQuantumAxe;
    public static ItemQuantumPick ItemQuantumPick;
    public static ItemQuantumShovel ItemQuantumShovel;
    public static ItemPlaceholder ItemRPlaceHolder;
    public static ItemBase ItemHyperConductor;
    public static ItemBase ItemQuantumPlating;
    public static ItemBase ItemQuantumConverter;
    /* BLOCKS */
    public static BlockOre OreUnbioxenium;
    public static BlockOreQuantonium OreQuantonium;
    public static Block BlockMachineCasing;
    /* MACHINE BLOCKS */
    public static BlockQDeenergizer BlockQDeenergizer;
    public static BlockQEInjector BlockQEInjector;
    public static BlockQDislocator BlockQDislocator;
    public static BlockQEnergySucker BlockQEnergySucker;
    public static BlockQDematerializer BlockQDematerializer;
    public static BlockQEExtractor BlockQEExtractor;
    public static BlockQCapacitor BlockQCapacitor;
    public static BlockIONForge BlockIONForge;
    public static BlockIONTunneler BlockIONTunneler;
    public static BlockIONScanner BlockIONScanner;
    public static BlockIONHarvester BlockIONHarvester;
    public static BlockQInterdimensionalGenerator BlockQInterdimensionalGenerator;
    public static BlockQCapacitor[] capacitors = new BlockQCapacitor[5];
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
        initOreDict();
        CraftingManager.addCrafting();
        CraftingManager.addSmelting();
        CraftingManager.addQDE();
    }

    public static void initOreDict() {
        OreDictionary.registerOre("oreQuantonium", new ItemStack(OreQuantonium));
        OreDictionary.registerOre("oreUnbioxenium", new ItemStack(OreUnbioxenium));
    }

    public static void initTabs() {
        tabQuantumCraft = new TabQuantumCraft();
    }

    public static void initItems() {
        ItemIngotUnbioxenium = (ItemBase) new ItemBase(Config.ItemUnbioxeniumIngotID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemUnbioxeniumIngot)
                .setTextureName(Config.getTextureName(Config.NameItemUnbioxeniumIngot));
        LanguageRegistry.addName(ItemIngotUnbioxenium, "Unbioxenium Ingot");

        ItemCrystalQuantonium =
                (ItemBase) new ItemBase(Config.ItemCrystallizedQuantoniumID).setCreativeTab(tabQuantumCraft)
                        .setUnlocalizedName(Config.NameItemCrystalQuantonium)
                        .setTextureName(Config.getTextureName(Config.NameItemCrystalQuantonium));
        LanguageRegistry.addName(ItemCrystalQuantonium, "Crystallized Quantonium");

        ItemRawQuantonium = (ItemBase) new ItemBase(Config.ItemRawQuantoniumID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemRawQuantonium)
                .setTextureName(Config.getTextureName(Config.NameItemRawQuantonium));
        LanguageRegistry.addName(ItemRawQuantonium, "Raw Quantonium");

        ItemMultiTool = (ItemMultiTool) new ItemMultiTool(Config.ItemMultiToolID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemMultiTool)
                .setTextureName(Config.getTextureName(Config.NameItemMultiTool));
        LanguageRegistry.addName(ItemMultiTool, "Quantum Multi Tool");

        ItemDepletedCrystal = (ItemBase) new ItemBase(Config.ItemDepletedCrystalID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemDepletedCrystal)
                .setTextureName(Config.getTextureName(Config.NameItemDepletedCrystal));
        LanguageRegistry.addName(ItemDepletedCrystal, "Depleted Crystal");

        ItemUpgrade = (ItemUpgrade) new ItemUpgrade(Config.ItemUpgradeID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemUpgrade_).setTextureName("USELESS");
        LanguageRegistry.addName(ItemUpgrade, "Upgrade");

        if (Config.beta.getBoolean(false)) {
            ItemResearchBook =
                    (ItemResearchBook) new ItemResearchBook(Config.ItemResearchBookID).setCreativeTab(tabQuantumCraft)
                            .setUnlocalizedName(Config.NameItemResearchBook)
                            .setTextureName(Config.getTextureName(Config.NameItemResearchBook));
            LanguageRegistry.addName(ItemResearchBook, "Research Book");

            ItemLocationCard = (ItemLocationCard) new ItemLocationCard(Config.ItemLocationCardID)
                    .setUnlocalizedName(Config.NameItemLocationCard)
                    .setTextureName(Config.getTextureName(Config.NameItemLocationCard));
            LanguageRegistry.addName(ItemLocationCard, "Location Card");

            ItemLocationCardBlank = (ItemLocationCardBlank) new ItemLocationCardBlank(Config.ItemLocationdCardBlankID)
                    .setUnlocalizedName(Config.NameItemLocationCardBlank).setCreativeTab(tabQuantumCraft)
                    .setTextureName(Config.getTextureName(Config.NameItemLocationCardBlank));
            LanguageRegistry.addName(ItemLocationCardBlank, "Location Card");
        }

        ItemPortableQCapacitor = (ItemPortableQCapacitor) new ItemPortableQCapacitor(Config.ItemPortQCapacitorID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameItemPortableQCapacitor)
                .setTextureName(Config.getTextureName(Config.NameItemPortableQCapacitor));
        LanguageRegistry.addName(ItemPortableQCapacitor, "Portable Quantum Capacitor");

        ItemInfinitePower =
                (ItemInfinitePower) new ItemInfinitePower(Config.ItemInfinitePowerID).setCreativeTab(tabQuantumCraft)
                        .setUnlocalizedName("ItemInfinitePower");
        LanguageRegistry.addName(ItemInfinitePower, "Infinite Power!");

        ItemQuantumSword = (ItemQuantumSword) new ItemQuantumSword(Config.ItemQSwordID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQSword).setTextureName(Config.getTextureName(Config.NameItemQSword));
        LanguageRegistry.addName(ItemQuantumSword, "Quantum Sword");

        ItemQuantumAxe = (ItemQuantumAxe) new ItemQuantumAxe(Config.ItemQAxeID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQAxe).setTextureName(Config.getTextureName(Config.NameItemQAxe));
        LanguageRegistry.addName(ItemQuantumAxe, "Quantum Axe");

        ItemQuantumPick = (ItemQuantumPick) new ItemQuantumPick(Config.ItemQPickID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQPick).setTextureName(Config.getTextureName(Config.NameItemQPick));
        LanguageRegistry.addName(ItemQuantumPick, "Quantum Pickaxe");

        ItemQuantumShovel =
                (ItemQuantumShovel) new ItemQuantumShovel(Config.ItemQShovelID).setCreativeTab(tabQuantumCraft)
                        .setUnlocalizedName(Config.NameItemQShovel)
                        .setTextureName(Config.getTextureName(Config.NameItemQShovel));
        LanguageRegistry.addName(ItemQuantumShovel, "Quantum Shovel");

        ItemRPlaceHolder = (ItemPlaceholder) new ItemPlaceholder(Config.ItemRPlaceHolderID).setCreativeTab(null)
                .setUnlocalizedName(Config.NameItemRPlaceHolder)
                .setTextureName(Config.getTextureName(Config.NameItemRPlaceHolder));
        LanguageRegistry.addName(ItemRPlaceHolder, "Render Placeholder");

        ItemHyperConductor = (ItemBase) new ItemBase(Config.ItemHyperConductorID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemHyperConductor)
                .setTextureName(Config.getTextureName(Config.NameItemHyperConductor));
        LanguageRegistry.addName(ItemHyperConductor, "Hyper Conductor");

        ItemQuantumPlating = (ItemBase) new ItemBase(Config.ItemQuantumPlatingID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQuantumPlating)
                .setTextureName(Config.getTextureName(Config.NameItemQuantumPlating));
        LanguageRegistry.addName(ItemQuantumPlating, "Quantum Plating");

        ItemQuantumConverter = (ItemBase) new ItemBase(Config.ItemQuantumConverterID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQuantumConverter)
                .setTextureName(Config.getTextureName(Config.NameItemQuantumConverter));
        LanguageRegistry.addName(ItemQuantumConverter, "Quantum Converter");
    }

    public static void initBlocks() {
        OreUnbioxenium = (BlockOre) new BlockOre().setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameOreUnbioxenium)
                .setBlockTextureName(Config.getTextureName(Config.NameOreUnbioxenium)).setHardness(2.5F);
        GameRegistry.registerBlock(OreUnbioxenium, Config.NameOreUnbioxenium);

        OreQuantonium =
                (BlockOreQuantonium) new BlockOreQuantonium().setCreativeTab(tabQuantumCraft)
                        .setBlockName(Config.NameOreQuantonium)
                        .setBlockTextureName(Config.getTextureName(Config.NameOreQuantonium));
        GameRegistry.registerBlock(OreQuantonium, Config.NameOreQuantonium);

        BlockQuantumFiberWire = (BlockQuantumFiberWire) new BlockQuantumFiberWire(Config.BlockFiberWireID)
                .setCreativeTab(tabQuantumCraft).setBlockName(Config.NameBlockQFiberWire);
        GameRegistry.registerBlock(BlockQuantumFiberWire, Config.NameBlockQFiberWire);

        BlockQDeenergizer = (BlockQDeenergizer) new BlockQDeenergizer(Config.BlockQDEID).setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockQDE);
        GameRegistry.registerBlock(BlockQDeenergizer, Config.NameBlockQDE);

        BlockQDematerializer =
                (BlockQDematerializer) new BlockQDematerializer(Config.BlockQDMID).setCreativeTab(tabQuantumCraft)
                        .setBlockName(Config.NameBlockQDM);
        GameRegistry.registerBlock(BlockQDematerializer, Config.NameBlockQDM);

        BlockQEInjector = (BlockQEInjector) new BlockQEInjector(Config.BlockQEIID).setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockQEI);
        GameRegistry.registerBlock(BlockQEInjector, Config.NameBlockQEI);

        BlockIONForge = (BlockIONForge) new BlockIONForge(Config.BlockIOFID).setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockIOF);
        GameRegistry.registerBlock(BlockIONForge, Config.NameBlockIOF);

        BlockIONTunneler = (BlockIONTunneler) new BlockIONTunneler(Config.BlockIOTID).setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockIOT);
        GameRegistry.registerBlock(BlockIONTunneler, Config.NameBlockIOT);

        BlockIONScanner = (BlockIONScanner) new BlockIONScanner(Config.BlockIOSID).setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockIOS);
        GameRegistry.registerBlock(BlockIONScanner, Config.NameBlockIOS);


        BlockIONHarvester = (BlockIONHarvester) new BlockIONHarvester(Config.BlockIOHID).setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockIOH);
        GameRegistry.registerBlock(BlockIONHarvester, Config.NameBlockIOH);

        BlockQInterdimensionalGenerator =
                (BlockQInterdimensionalGenerator) new BlockQInterdimensionalGenerator(Config.BlockQIGID)
                        .setCreativeTab(tabQuantumCraft).setBlockName(Config.NameBlockQIG);
        GameRegistry.registerBlock(BlockQInterdimensionalGenerator, Config.NameBlockQIG);

        for (int i = 1; i <= 5; i++) {
            BlockQCapacitor = (BlockQCapacitor) new BlockQCapacitor(Config.BlockQCPID.getInt() + i)
                    .setCreativeTab(tabQuantumCraft).setBlockName(Config.NameBlockQCP + i);
            BlockQCapacitor.setMaxEnergyMultiplier(i);
            capacitors[i - 1] = BlockQCapacitor;
            LanguageRegistry.addName(BlockQCapacitor, "Quantum " + CapacitorName.getName(i) + " Capacitor");
            GameRegistry.registerBlock(BlockQCapacitor, Config.NameBlockQCP + i);
        }
        if (Config.beta.getBoolean(false)) {
            BlockQDislocator =
                    (BlockQDislocator) new BlockQDislocator(Config.BlockQDSID).setCreativeTab(tabQuantumCraft)
                            .setBlockName(Config.NameBlockQDS);
            GameRegistry.registerBlock(BlockQDislocator, Config.NameBlockQDS);

            BlockQEnergySucker =
                    (BlockQEnergySucker) new BlockQEnergySucker(Config.BlockQESID).setCreativeTab(tabQuantumCraft)
                            .setBlockName(Config.NameBlockQES);
            GameRegistry.registerBlock(BlockQEnergySucker, Config.NameBlockQES);
        }

        BlockQEExtractor = (BlockQEExtractor) new BlockQEExtractor(Config.BlockQEEID).setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockQEE);
        GameRegistry.registerBlock(BlockQEExtractor, Config.NameBlockQEE);

        BlockMachineCasing = new Block(Material.rock).setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockMCasing)
                .setTextureName(Config.getTextureName(Config.NameBlockMCasing));
        GameRegistry.registerBlock(BlockMachineCasing, Config.NameBlockMCasing);

    }

    public static void initRenderers() {
        RenderingRegistry.registerBlockHandler(RenderOre.instance().getRenderId(), RenderOre.instance());
        RenderingRegistry.registerBlockHandler(RenderFiberWire.instance().getRenderId(), RenderFiberWire.instance());
    }

    public static void initWGen() {
        GameRegistry.registerWorldGenerator(new WorldGen(), 1);
    }

    public static void initTEs() {
        GameRegistry.registerTileEntity(TileQDeenergizer.class, "QDeenergizerTE");
        GameRegistry.registerTileEntity(TileQEInjector.class, "QEInjectorTE");
        GameRegistry.registerTileEntity(TileQEnergySucker.class, "QESuckerTE");
        GameRegistry.registerTileEntity(TileQDematerializer.class, "QDematerializerTE");
        GameRegistry.registerTileEntity(TileQEExtractor.class, "QEExtractorTE");
        GameRegistry.registerTileEntity(TileIONForge.class, "IONForgeTE");
        GameRegistry.registerTileEntity(TileIONHarvester.class, "IONHarvesterTE");
        GameRegistry.registerTileEntity(TileIONScanner.class, "IONScannerTE");
        GameRegistry.registerTileEntity(TileIONTunneler.class, "IONTunnelerTE");
        GameRegistry.registerTileEntity(TileQCapacitor.class, "QCapacitorTE");
        GameRegistry.registerTileEntity(TileQInterdimensionalGenerator.class, "QInterdimensionalGeneratorTE");

        if (Config.beta.getBoolean(false)) {
            GameRegistry.registerTileEntity(TileQDislocator.class, "QDislocatorTE");
        }
    }

    public static class ToolMaterials {

        //The following values are subject to change.
        public static Item.ToolMaterial QUANTUMTOOL = EnumHelper.addToolMaterial("QUANTUMTOOL", 3, 0, 7.0F, 2, 30);
        //the 3rd argument (0) is not used...
    }

    public static class IconLoader {

        public static IIcon oreQuantonium_ore;
        public static IIcon oreQuantonium_base;

        public static void loadAll(IIconRegister i) {
            QuantumCraft.logHandler.debugPrint(Config.getTextureName(Config.NameTextureQOre));
            oreQuantonium_ore = i.registerIcon(Config.getTextureName(Config.NameTextureQOre));
            QuantumCraft.logHandler.debugPrint(Config.getTextureName(Config.NameTextureQBase));
            oreQuantonium_base = i.registerIcon(Config.getTextureName(Config.NameTextureQBase));
        }
    }

}
