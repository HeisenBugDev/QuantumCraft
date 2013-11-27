package quantumcraft.core;

import buildcraft.BuildCraftTransport;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingManager {

    public static void addCrafting() {

        ItemStack ironStack = new ItemStack(Loader.ItemIngotUnbioxenium);
        ItemStack crystalStack = new ItemStack(Loader.ItemCrystalQuantonium);

        // QUATUM PLATING    x4
        GameRegistry
                .addShapedRecipe(new ItemStack(Loader.ItemQuantumPlating, 4), "xxx", "xyx", "xxx", 'x', ironStack, 'y',
                        crystalStack);
        // HYPER CONDUCTOR   x6
        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemHyperConductor, 6), "xxx", "yyy", "xxx", 'x',
                new ItemStack(Block.glass), 'y', crystalStack);
        // FIBER WIRE        x6
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockQuantumFiberWire, 6), "xxx", "yyy", "xxx", 'x',
                new ItemStack(Loader.ItemQuantumPlating), 'y', new ItemStack(Loader.ItemHyperConductor));
        // MULTI TOOL        x1
        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemMultiTool, 1), " i ", "rii", " r ", 'i', ironStack, 'r',
                new ItemStack(Item.dyePowder, 1, 1));

        // BLANK UPGRADE
        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemUpgrade, 1, 1), "bbb", "bab", "bbb", 'b',
                Loader.ItemQuantumPlating, 'a', Loader.ItemHyperConductor);

        if (Config.beta.getBoolean(false)) {
            GameRegistry.addShapelessRecipe(new ItemStack(Loader.ItemLocationCardBlank),
                    new ItemStack(Loader.ItemLocationCard));
            // BLANK LOC CARD    x2 <-- cannot do 2 cause maxstacksize is 1
            GameRegistry.addShapelessRecipe(new ItemStack(Loader.ItemLocationCardBlank), new ItemStack(Item.paper),
                    new ItemStack(Item.paper), new ItemStack(Item.dyePowder, 1, 0));
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
                new ItemStack(Block.glass), 'e', new ItemStack(Item.enderPearl));
        // INJECTOR
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockQEInjector, 1), "ccc", "pmp", "pxp", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 'x',
                new ItemStack(Loader.ItemHyperConductor), 'm', new ItemStack(Loader.BlockMachineCasing));
        GameRegistry
                .addShapelessRecipe(new ItemStack(Loader.BlockQEInjector, 1), new ItemStack(Loader.BlockQEExtractor));
        // EXTRACTOR
        GameRegistry
                .addShapelessRecipe(new ItemStack(Loader.BlockQEExtractor, 1), new ItemStack(Loader.BlockQEInjector));

        // [todo] - Capacitor recipe

        if (Loader.hasBuildCraft()) {
            // ELECTRIFIER
            GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockQElectrifier, 1), "cxc", "kmk", "ppp", 'p',
                    new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 'x',
                    new ItemStack(Loader.ItemHyperConductor), 'm', new ItemStack(Loader.BlockMachineCasing), 'k',
                    new ItemStack(BuildCraftTransport.pipePowerWood));
            GameRegistry.addShapelessRecipe(new ItemStack(Loader.BlockQElectrifier, 1),
                    new ItemStack(Loader.BlockQDeelectrifier));
            // DEELECTRIFIER
            GameRegistry.addShapelessRecipe(new ItemStack(Loader.BlockQDeelectrifier, 1),
                    new ItemStack(Loader.BlockQElectrifier));
        }
        // ION Forge
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockIONForge, 1), "aaa", "cbc", "aaa", 'a',
                new ItemStack(Loader.ItemIngotUnbioxenium), 'b', new ItemStack(Block.furnaceIdle), 'c',
                new ItemStack(Loader.ItemHyperConductor));

        // ION Tunneler
        GameRegistry.addShapedRecipe(new ItemStack(Loader.BlockIONTunneler, 1), "dpd", "lml", "uhu", 'd',
                new ItemStack(Item.diamond), 'p', new ItemStack(Item.pickaxeDiamond), 'l',
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

        // TOOLS
        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemQuantumAxe, 1), "ccp", "csp", " s ", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 's', new ItemStack(Item.stick));

        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemQuantumPick, 1), "ccc", "psp", " s ", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 's', new ItemStack(Item.stick));

        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemQuantumShovel, 1), "pcp", " s ", " s ", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 's', new ItemStack(Item.stick));

        GameRegistry.addShapedRecipe(new ItemStack(Loader.ItemQuantumSword, 1), "pcp", "pcp", " s ", 'p',
                new ItemStack(Loader.ItemQuantumPlating), 'c', crystalStack, 's', new ItemStack(Item.stick));

    }

    public static void addSmelting() {
        GameRegistry.addSmelting(Loader.ItemRawQuantonium.itemID, new ItemStack(Loader.ItemCrystalQuantonium), 2);
        GameRegistry.addSmelting(Loader.OreUnbioxenium.blockID, new ItemStack(Loader.ItemIngotUnbioxenium), 1);
    }

    public static void addQDE() {
        QRecipeHandler.addQDERecipe(new QDERecipe(new ItemStack(Loader.ItemCrystalQuantonium), 100));
    }

}
