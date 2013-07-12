package mods.quantumcraft.core;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.ItemStack;

import java.util.LinkedList;

public class QRecipeHandler {

    private static LinkedList<QDERecipe> QDERecipes = new LinkedList<QDERecipe>();

    public static void addQDERecipe(QDERecipe r) {
        if (QDERecipes == null) FMLLog.severe("QDE Recipes list seeems to be null!");
        QDERecipes.add(r);
    }

    public static QDERecipe getQDERecipeFromInput(ItemStack input) {
        if (input == null) return null;
        for (QDERecipe r : QDERecipes) {
            if (r.getInputItem().itemID == input.itemID) return r;
        }
        return null;
    }


}