package quantumcraft.core;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
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
        OreDictionary.registerOre("ore_quantonium", new ItemStack(OreQuantonium));
        OreDictionary.registerOre("ore_unbioxenium", new ItemStack(OreUnbioxenium));
    }

    public static void initTabs() {
        tabQuantumCraft = new TabQuantumCraft();
    }

    public static void initItems() {
        ItemIngotUnbioxenium = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemUnbioxeniumIngot)
                .setTextureName(Config.getTextureName(Config.NameItemUnbioxeniumIngot));
        GameRegistry.registerItem(ItemIngotUnbioxenium, Config.NameItemUnbioxeniumIngot);

        ItemCrystalQuantonium = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemCrystalQuantonium)
                .setTextureName(Config.getTextureName(Config.NameItemCrystalQuantonium));
        GameRegistry.registerItem(ItemCrystalQuantonium, Config.NameItemCrystalQuantonium);

        ItemRawQuantonium = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemRawQuantonium)
                .setTextureName(Config.getTextureName(Config.NameItemRawQuantonium));
        GameRegistry.registerItem(ItemRawQuantonium, Config.NameItemRawQuantonium);

        ItemMultiTool = (ItemMultiTool) new ItemMultiTool().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemMultiTool)
                .setTextureName(Config.getTextureName(Config.NameItemMultiTool));
        GameRegistry.registerItem(ItemMultiTool, Config.NameItemMultiTool);

        ItemDepletedCrystal = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemDepletedCrystal)
                .setTextureName(Config.getTextureName(Config.NameItemDepletedCrystal));
        GameRegistry.registerItem(ItemDepletedCrystal, Config.NameItemDepletedCrystal);

        ItemUpgrade = (ItemUpgrade) new ItemUpgrade().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemUpgrade_).setTextureName("USELESS");
        GameRegistry.registerItem(ItemUpgrade, Config.NameItemUpgrade_);

        if (Config.beta.getBoolean(false)) {
            ItemResearchBook = (ItemResearchBook) new ItemResearchBook().setCreativeTab(tabQuantumCraft)
                    .setUnlocalizedName(Config.NameItemResearchBook)
                    .setTextureName(Config.getTextureName(Config.NameItemResearchBook));
            GameRegistry.registerItem(ItemResearchBook, Config.NameItemResearchBook);

            ItemLocationCard = (ItemLocationCard) new ItemLocationCard().setUnlocalizedName(Config.NameItemLocationCard)
                    .setTextureName(Config.getTextureName(Config.NameItemLocationCard));
            GameRegistry.registerItem(ItemLocationCard, Config.NameItemLocationCard);

            ItemLocationCardBlank = (ItemLocationCardBlank) new ItemLocationCardBlank()
                    .setUnlocalizedName(Config.NameItemLocationCardBlank).setCreativeTab(tabQuantumCraft)
                    .setTextureName(Config.getTextureName(Config.NameItemLocationCardBlank));
            GameRegistry.registerItem(ItemLocationCardBlank, Config.NameItemLocationCardBlank);
        }

        ItemPortableQCapacitor = (ItemPortableQCapacitor) new ItemPortableQCapacitor().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemPortableQCapacitor)
                .setTextureName(Config.getTextureName(Config.NameItemPortableQCapacitor));
        GameRegistry.registerItem(ItemPortableQCapacitor, Config.NameItemPortableQCapacitor);

        ItemInfinitePower = (ItemInfinitePower) new ItemInfinitePower().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName("ItemInfinitePower");
        GameRegistry.registerItem(ItemInfinitePower, "ItemInfinitePower");

        ItemQuantumSword = (ItemQuantumSword) new ItemQuantumSword().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQSword).setTextureName(Config.getTextureName(Config.NameItemQSword));
        GameRegistry.registerItem(ItemQuantumSword, Config.NameItemQSword);

        ItemQuantumAxe = (ItemQuantumAxe) new ItemQuantumAxe().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQAxe).setTextureName(Config.getTextureName(Config.NameItemQAxe));
        GameRegistry.registerItem(ItemQuantumAxe, Config.NameItemQAxe);

        ItemQuantumPick = (ItemQuantumPick) new ItemQuantumPick().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQPick).setTextureName(Config.getTextureName(Config.NameItemQPick));
        GameRegistry.registerItem(ItemQuantumPick, Config.NameItemQPick);

        ItemQuantumShovel = (ItemQuantumShovel) new ItemQuantumShovel().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQShovel)
                .setTextureName(Config.getTextureName(Config.NameItemQShovel));
        GameRegistry.registerItem(ItemQuantumShovel, Config.NameItemQShovel);

        ItemRPlaceHolder = (ItemPlaceholder) new ItemPlaceholder().setCreativeTab(null)
                .setUnlocalizedName(Config.NameItemRPlaceHolder)
                .setTextureName(Config.getTextureName(Config.NameItemRPlaceHolder));
        GameRegistry.registerItem(ItemRPlaceHolder, Config.NameItemRPlaceHolder);

        ItemHyperConductor = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemHyperConductor)
                .setTextureName(Config.getTextureName(Config.NameItemHyperConductor));
        GameRegistry.registerItem(ItemHyperConductor, Config.NameItemHyperConductor);

        ItemQuantumPlating = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQuantumPlating)
                .setTextureName(Config.getTextureName(Config.NameItemQuantumPlating));
        GameRegistry.registerItem(ItemQuantumPlating, Config.NameItemQuantumPlating);

        ItemQuantumConverter = (ItemBase) new ItemBase().setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameItemQuantumConverter)
                .setTextureName(Config.getTextureName(Config.NameItemQuantumConverter));
        GameRegistry.registerItem(ItemQuantumConverter, Config.NameItemQuantumConverter);
    }

    public static void initBlocks() {
        OreUnbioxenium =
                (BlockOre) new BlockOre().setCreativeTab(tabQuantumCraft).setBlockName(Config.NameOreUnbioxenium)
                        .setBlockTextureName(Config.getTextureName(Config.NameOreUnbioxenium)).setHardness(2.5F);
        GameRegistry.registerBlock(OreUnbioxenium, Config.NameOreUnbioxenium);

        OreQuantonium = (BlockOreQuantonium) new BlockOreQuantonium().setCreativeTab(tabQuantumCraft)
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
            BlockQEnergySucker =
                    (BlockQEnergySucker) new BlockQEnergySucker(Config.BlockQESID).setCreativeTab(tabQuantumCraft)
                            .setBlockName(Config.NameBlockQES);
            GameRegistry.registerBlock(BlockQEnergySucker, Config.NameBlockQES);
        }

        BlockQEExtractor = (BlockQEExtractor) new BlockQEExtractor(Config.BlockQEEID).setCreativeTab(tabQuantumCraft)
                .setBlockName(Config.NameBlockQEE);
        GameRegistry.registerBlock(BlockQEExtractor, Config.NameBlockQEE);

        BlockMachineCasing = new BasicBlock().setCreativeTab(tabQuantumCraft).setBlockName(Config.NameBlockMCasing)
                .setBlockTextureName(Config.getTextureName(Config.NameBlockMCasing));
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
    }

    public static class ToolMaterials {

        //The following values are subject to change.
        public static Item.ToolMaterial QUANTUMTOOL = EnumHelper.addToolMaterial("QUANTUMTOOL", 3, 0, 7.0F, 2, 30);
        //the 3rd argument (0) is not used...
    }

    public static class IconLoader {

        public static IIcon quantonium_ore;
        public static IIcon quantonium_ore_base;

        public static void loadAll(IIconRegister i) {
            QuantumCraft.logHandler.debugPrint(Config.getTextureName(Config.NameTextureQOre));
            quantonium_ore = i.registerIcon(Config.getTextureName(Config.NameTextureQOre));
            QuantumCraft.logHandler.debugPrint(Config.getTextureName(Config.NameTextureQBase));
            quantonium_ore_base = i.registerIcon(Config.getTextureName(Config.NameTextureQBase));
        }
    }

}
