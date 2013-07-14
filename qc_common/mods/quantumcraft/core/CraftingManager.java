package mods.quantumcraft.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class CraftingManager {

    public static void addCrafting() {
        GameRegistry.addShapelessRecipe(new ItemStack(Loader.ItemLocationCard), Loader.ItemLocationCard);

    }

    public static void addSmelting() {
        GameRegistry.addSmelting(Loader.ItemRawQuantonium.itemID, new ItemStack(Loader.ItemCrystalQuantonium), 2);
    }

    public static void addQDE() {
        QRecipeHandler.addQDERecipe(new QDERecipe(new ItemStack(Loader.ItemCrystalQuantonium), 100));
    }

}
