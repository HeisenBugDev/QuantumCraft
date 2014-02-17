package quantumcraft.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingManager {

    public static void addCrafting() {

        ItemStack unbioxeniumStack = new ItemStack(Loader.ItemIngotUnbioxenium);
        ItemStack crystalStack = new ItemStack(Loader.ItemCrystalQuantonium);

        // QUATUM PLATING    x4
        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemQuantumPlating, 4), "xxx", "xyx", "xxx", 'x',
                unbioxeniumStack, 'y', crystalStack);
        // HYPER CONDUCTOR   x6
        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemHyperConductor, 6), "xxx", "yyy", "xxx", 'x',
                new ItemStack(Blocks.glass), 'y', crystalStack);
        // Quantum Converter x1
        GameRegistry.addShapelessRecipe(new ItemStack(Loader.ItemQuantumConverter, 1), crystalStack, unbioxeniumStack,
                Loader.ItemHyperConductor, Loader.ItemQuantumPlating);

        // FIBER WIRE        x18
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockQuantumFiberWire, 18), "xxx", "yyy", "xxx", 'x',
                new ItemStack(Loader.ItemQuantumPlating), 'y', new ItemStack(Loader.ItemHyperConductor));
        // MULTI TOOL        x1
        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemMultiTool, 1), " i ", "rii", " r ", 'i', unbioxeniumStack,
                'r', new ItemStack(Items.dye, 1, 1));

        // BLANK UPGRADE
        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemUpgrade, 1, 1), "bbb", "bab", "bbb", 'b',
                Loader.ItemQuantumPlating, 'a', Loader.ItemHyperConductor);

        if (Config.beta.getBoolean(false)) {
            GameRegistry.addShapelessRecipe(new ItemStack(Loader.ItemLocationCardBlank),
                    new ItemStack(Loader.ItemLocationCard));
            // BLANK LOC CARD    x2 <-- cannot do 2 cause maxstacksize is 1
            GameRegistry.addShapelessRecipe(new ItemStack(Loader.ItemLocationCardBlank), new ItemStack(Items.paper),
                    new ItemStack(Items.paper), new ItemStack(Items.dye, 1, 0));

            // Q Interdimensional Generator
            GameRegistry
                    .addShapedRecipe(new ItemStack(Loader.BlockQInterdimensionalGenerator, 1), "ucu", "kmk", "ucu", 'u',
                            unbioxeniumStack, 'c', crystalStack, 'k', new ItemStack(Loader.ItemQuantumConverter), 'm',
                            new ItemStack(Loader.BlockMachineCasing));
        }
        // PORT. CAPACITOR   x1
        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemPortableQCapacitor, 1), "pcp", "xcx", "pcp", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 'x',
                new ItemStack(Loader.ItemHyperConductor));
        // MACHINE CASING
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockMachineCasing, 1), "ppp", "p p", "ppp", 'p',
                new ItemStack(Loader.ItemQuantumPlating));
        // DEENERGIZER
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockQDeenergizer, 1), "pcp", "xmx", "ppp", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 'x',
                new ItemStack(Loader.ItemHyperConductor), 'm', new ItemStack(Loader.BlockMachineCasing));
        // DEMATERIALIZER
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockQDematerializer, 1), "cxc", "geg", "pmp", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 'x',
                new ItemStack(Loader.ItemHyperConductor), 'm', new ItemStack(Loader.BlockMachineCasing), 'g',
                new ItemStack(Blocks.glass), 'e', new ItemStack(Items.ender_pearl));
        // INJECTOR
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockQEInjector, 1), "ccc", "pmp", "pxp", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 'x',
                new ItemStack(Loader.ItemHyperConductor), 'm', new ItemStack(Loader.BlockMachineCasing));
        GameRegistry
                .addShapelessRecipe(new ItemStack(Loader.BlockQEInjector, 1), new ItemStack(Loader.BlockQEExtractor));
        // EXTRACTOR
        GameRegistry
                .addShapelessRecipe(new ItemStack(Loader.BlockQEExtractor, 1), new ItemStack(Loader.BlockQEInjector));

        // ION Forge
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockIONForge, 1), "aaa", "cbc", "aaa", 'a',
                new ItemStack(Loader.ItemIngotUnbioxenium), 'b', new ItemStack(Blocks.furnace), 'c',
                new ItemStack(Loader.ItemHyperConductor));

        // ION Tunneler
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockIONTunneler, 1), "dpd", "lml", "uhu", 'd',
                new ItemStack(Items.diamond), 'p', new ItemStack(Items.diamond_pickaxe), 'l',
                new ItemStack(Loader.ItemQuantumPlating), 'm', new ItemStack(Loader.BlockMachineCasing), 'u',
                new ItemStack(Loader.ItemIngotUnbioxenium), 'h', new ItemStack(Loader.ItemHyperConductor));

        // ION Scanner
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockIONScanner, 1), "hih", "qmq", "hih", 'h',
                new ItemStack(Loader.ItemHyperConductor), 'i', new ItemStack(Loader.ItemIngotUnbioxenium), 'q',
                new ItemStack(Loader.ItemQuantumPlating), 'm', new ItemStack(Loader.BlockMachineCasing));

        // ION Harvester
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockIONHarvester, 1), "iii", "qmq", "hhh", 'i',
                new ItemStack(Loader.ItemIngotUnbioxenium), 'q', new ItemStack(Loader.ItemQuantumPlating), 'm',
                new ItemStack(Loader.BlockMachineCasing), 'h', new ItemStack(Loader.ItemHyperConductor));

        // Basic Capacitor
        GameRegistry.addShapedRecipe(new ItemStack(Loader.capacitors[0]), "cpc", "hmh", "cpc", 'c',
                new ItemStack(Loader.ItemHyperConductor), 'p', new ItemStack(Loader.ItemQuantumPlating), 'h',
                new ItemStack(Loader.ItemHyperConductor), 'm', new ItemStack(Loader.BlockMachineCasing));

        for (int c = 1; c < Loader.capacitors.length; c++) {
            GameRegistry.addShapedRecipe(new ItemStack(Loader.capacitors[c]), "cpc", "hmh", "cpc", 'c',
                    new ItemStack(Loader.capacitors[c - 1]), 'p', new ItemStack(Loader.ItemQuantumPlating), 'h',
                    new ItemStack(Loader.ItemHyperConductor), 'm', new ItemStack(Loader.BlockMachineCasing));
        }
        // TOOLS
        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemQuantumAxe, 1), "ccp", "csp", " s ", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 's', new ItemStack(Items.stick));

        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemQuantumPick, 1), "ccc", "psp", " s ", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 's', new ItemStack(Items.stick));

        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemQuantumShovel, 1), "pcp", " s ", " s ", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 's', new ItemStack(Items.stick));

        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemQuantumSword, 1), "pcp", "pcp", " s ", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 's', new ItemStack(Items.stick));

    }

    public static void addSmelting() {
        GameRegistry.addSmelting(Loader.ItemRawQuantonium, new ItemStack(Loader.ItemCrystalQuantonium), 2);
        GameRegistry.addSmelting(Loader.OreUnbioxenium, new ItemStack(Loader.ItemIngotUnbioxenium), 1);
    }

    public static void addQDE() {
        QRecipeHandler.addQDERecipe(new QDERecipe(new ItemStack(Loader.ItemCrystalQuantonium), 1100));
    }

}
