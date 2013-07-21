package mods.quantumcraft.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class CraftingManager {

    public static void addCrafting() {
        GameRegistry.addShapelessRecipe(new ItemStack(Loader.ItemLocationCard), new ItemStack(Loader.ItemLocationCard, 1, 1));

    }

    public static void addSmelting() {
        GameRegistry.addSmelting(Loader.ItemRawQuantonium.itemID, new ItemStack(Loader.ItemCrystalQuantonium), 2);
        GameRegistry.addSmelting(Loader.OrePlutonium.blockID, new ItemStack(Loader.ItemIngotPlutonium), 1);
    }

    public static void addQDE() {
        QRecipeHandler.addQDERecipe(new QDERecipe(new ItemStack(Loader.ItemCrystalQuantonium), 100));
    }

}
