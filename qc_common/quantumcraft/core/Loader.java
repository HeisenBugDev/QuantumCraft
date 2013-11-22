package quantumcraft.core;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.common.EnumHelper;
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
    public static ItemUpgrade ItemUpgrade;
    public static ItemQuantumSword ItemQuantumSword;
    public static ItemQuantumAxe ItemQuantumAxe;
    public static ItemQuantumPick ItemQuantumPick;
    public static ItemQuantumShovel ItemQuantumShovel;
    public static ItemPlaceholder ItemRPlaceHolder;
    public static ItemBase ItemHyperConductor;
    public static ItemBase ItemQuantumPlating;
    /* BLOCKS */
    public static BlockOre OreUnbioxenium;
    public static BlockOreQuantonium OreQuantonium;
    /* MACHINE BLOCKS */
    public static BlockQDeenergizer BlockQDeenergizer;
    public static BlockQEInjector BlockQEInjector;
    public static BlockQDislocator BlockQDislocator;
    public static BlockQEnergySucker BlockQEnergySucker;
    public static BlockQDematerializer BlockQDematerializer;
    public static BlockQElectrifier BlockQElectrifier;
    public static BlockQEExtractor BlockQEExtractor;
    public static BlockQDeelectrifier BlockQDeelectrifier;
    public static BlockQCapacitor BlockQCapacitor;
    public static BlockIONForge BlockIONForge;
    public static BlockIONTunneler BlockIONTunneler;
    public static BlockIONScanner BlockIONScanner;
    public static BlockIONHarvester BlockIONHarvester;
    public static Block BlockMachineCasing;
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
    }

    public static void initBlocks() {
        OreUnbioxenium = (BlockOre) new BlockOre(Config.OreUnbioxeniumID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameOreUnbioxenium)
                .setTextureName(Config.getTextureName(Config.NameOreUnbioxenium)).setHardness(2.5F);
        LanguageRegistry.addName(OreUnbioxenium, "Unbioxenium Ore");
        GameRegistry.registerBlock(OreUnbioxenium, Config.NameOreUnbioxenium);

        OreQuantonium =
                (BlockOreQuantonium) new BlockOreQuantonium(Config.OreQuantoniumID).setCreativeTab(tabQuantumCraft)
                        .setUnlocalizedName(Config.NameOreQuantonium)
                        .setTextureName(Config.getTextureName(Config.NameOreQuantonium));
        LanguageRegistry.addName(OreQuantonium, "Quantonium Ore");
        GameRegistry.registerBlock(OreQuantonium, Config.NameOreQuantonium);

        BlockQuantumFiberWire = (BlockQuantumFiberWire) new BlockQuantumFiberWire(Config.BlockFiberWireID)
                .setCreativeTab(tabQuantumCraft).setUnlocalizedName(Config.NameBlockQFiberWire);
        LanguageRegistry.addName(BlockQuantumFiberWire, "Quantum Fiber-Wire");
        GameRegistry.registerBlock(BlockQuantumFiberWire, Config.NameBlockQFiberWire);

        BlockQDeenergizer = (BlockQDeenergizer) new BlockQDeenergizer(Config.BlockQDEID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameBlockQDE).setTextureName(Config.getTextureName(Config.NameBlockQDE));
        LanguageRegistry.addName(BlockQDeenergizer, "Quantum De-Energizer");
        GameRegistry.registerBlock(BlockQDeenergizer, Config.NameBlockQDE);

        BlockQDematerializer =
                (BlockQDematerializer) new BlockQDematerializer(Config.BlockQDMID).setCreativeTab(tabQuantumCraft)
                        .setUnlocalizedName(Config.NameBlockQDM)
                        .setTextureName(Config.getTextureName(Config.NameBlockQDM));
        LanguageRegistry.addName(BlockQDematerializer, "Quantum De-Materializer");
        GameRegistry.registerBlock(BlockQDematerializer, Config.NameBlockQDM);

        BlockQEInjector = (BlockQEInjector) new BlockQEInjector(Config.BlockQEIID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameBlockQEI).setTextureName(Config.getTextureName(Config.NameBlockQEI));
        LanguageRegistry.addName(BlockQEInjector, "Quantum Energy Injector");
        GameRegistry.registerBlock(BlockQEInjector, Config.NameBlockQEI);

        BlockIONForge = (BlockIONForge) new BlockIONForge(Config.BlockIOFID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameBlockIOF).setTextureName(Config.getTextureName(Config.NameBlockIOF));
        LanguageRegistry.addName(BlockIONForge, "ION Forge");
        GameRegistry.registerBlock(BlockIONForge, Config.NameBlockIOF);

        BlockIONTunneler = (BlockIONTunneler) new BlockIONTunneler(Config.BlockIOTID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameBlockIOT).setTextureName(Config.getTextureName(Config.NameBlockIOT));
        LanguageRegistry.addName(BlockIONTunneler, "ION Tunneler");
        GameRegistry.registerBlock(BlockIONTunneler, Config.NameBlockIOT);

        BlockIONScanner = (BlockIONScanner) new BlockIONScanner(Config.BlockIOSID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameBlockIOS).setTextureName(Config.getTextureName(Config.NameBlockIOS));
        LanguageRegistry.addName(BlockIONScanner, "ION Scanner");
        GameRegistry.registerBlock(BlockIONScanner, Config.NameBlockIOS);


        BlockIONHarvester = (BlockIONHarvester) new BlockIONHarvester(Config.BlockIOHID).setCreativeTab(tabQuantumCraft)
                // [bug] - @sammko, is this needed? The textures are being controlled elsewhere.
                .setUnlocalizedName(Config.NameBlockIOH).setTextureName(Config.getTextureName(Config.NameBlockIOH));
        LanguageRegistry.addName(BlockIONHarvester, "ION Harvester");
        GameRegistry.registerBlock(BlockIONHarvester, Config.NameBlockIOH);

        if (Config.beta.getBoolean(false)) {
            BlockQCapacitor = (BlockQCapacitor) new BlockQCapacitor(Config.BlockQCPID).setCreativeTab(tabQuantumCraft)
                    .setUnlocalizedName(Config.NameBlockQCP).setTextureName(Config.getTextureName(Config.NameBlockQCP));
            LanguageRegistry.addName(BlockQCapacitor, "Quantum Capacitor");
            GameRegistry.registerBlock(BlockQCapacitor, Config.NameBlockQCP);

            BlockQDislocator =
                    (BlockQDislocator) new BlockQDislocator(Config.BlockQDSID).setCreativeTab(tabQuantumCraft)
                            .setUnlocalizedName(Config.NameBlockQDS)
                            .setTextureName(Config.getTextureName(Config.NameBlockQDS));
            LanguageRegistry.addName(BlockQDislocator, "Quantum Dislocator");
            GameRegistry.registerBlock(BlockQDislocator, Config.NameBlockQDS);

            BlockQEnergySucker =
                    (BlockQEnergySucker) new BlockQEnergySucker(Config.BlockQESID).setCreativeTab(tabQuantumCraft)
                            .setUnlocalizedName(Config.NameBlockQES)
                            .setTextureName(Config.getTextureName(Config.NameBlockQES));
            LanguageRegistry.addName(BlockQEnergySucker, "Quantum Energy Sucker");
            GameRegistry.registerBlock(BlockQEnergySucker, Config.NameBlockQES);
        }

        if (hasBuildCraft()) {
            BlockQElectrifier =
                    (BlockQElectrifier) new BlockQElectrifier(Config.BlockQELID).setCreativeTab(tabQuantumCraft)
                            .setUnlocalizedName(Config.NameBlockQEL)
                            .setTextureName(Config.getTextureName(Config.NameBlockQEL));
            LanguageRegistry.addName(BlockQElectrifier, "Quantum Electrifier");
            GameRegistry.registerBlock(BlockQElectrifier, Config.NameBlockQEL);

            BlockQDeelectrifier =
                    (BlockQDeelectrifier) new BlockQDeelectrifier(Config.BlockQDLID).setCreativeTab(tabQuantumCraft)
                            .setUnlocalizedName(Config.NameBlockQDL)
                            .setTextureName(Config.getTextureName(Config.NameBlockQDL));
            LanguageRegistry.addName(BlockQDeelectrifier, "Quantum Deelectrifier");
            GameRegistry.registerBlock(BlockQDeelectrifier, Config.NameBlockQDL);
        }

        BlockQEExtractor = (BlockQEExtractor) new BlockQEExtractor(Config.BlockQEEID).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameBlockQEE).setTextureName(Config.getTextureName(Config.NameBlockQEE));
        LanguageRegistry.addName(BlockQEExtractor, "Quantum Energy Extractor");
        GameRegistry.registerBlock(BlockQEExtractor, Config.NameBlockQEE);

        BlockMachineCasing = new Block(Config.BlockMCasingID, Material.rock).setCreativeTab(tabQuantumCraft)
                .setUnlocalizedName(Config.NameBlockMCasing)
                .setTextureName(Config.getTextureName(Config.NameBlockMCasing));
        LanguageRegistry.addName(BlockMachineCasing, "Quantum Machine Casing");
        GameRegistry.registerBlock(BlockMachineCasing, Config.NameBlockMCasing);

    }

    public static void initRenderers() {
        RenderingRegistry.registerBlockHandler(RenderOre.instance().getRenderId(), RenderOre.instance());
        RenderingRegistry.registerBlockHandler(RenderFiberWire.instance().getRenderId(), RenderFiberWire.instance());
    }

    public static void initWGen() {
        GameRegistry.registerWorldGenerator(new WorldGen());
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

        if (hasBuildCraft()) {
            GameRegistry.registerTileEntity(TileQElectrifier.class, "QElectrifierTE");
            GameRegistry.registerTileEntity(TileQDeelectrifier.class, "QDeelectrifier");
        }

        if (Config.beta.getBoolean(false)) {
            GameRegistry.registerTileEntity(TileQCapacitor.class, "QCapacitorTE");
            GameRegistry.registerTileEntity(TileQDislocator.class, "QDislocatorTE");
        }
    }

    public static boolean hasBuildCraft() {

        return cpw.mods.fml.common.Loader.isModLoaded("BuildCraft|Silicon");
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
