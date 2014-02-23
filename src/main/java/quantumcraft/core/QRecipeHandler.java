package quantumcraft.core;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.ItemStack;

import java.util.LinkedList;

public class QRecipeHandler {

    private static LinkedList<QDERecipe> QDERecipes = new LinkedList<QDERecipe>();

    public static void addQDERecipe(QDERecipe r) {
        if (QDERecipes == null) {
            FMLLog.severe("QDE Recipes list seems to be null! NOT ADDING");
        } else {
            QDERecipes.add(r);
        }
    }

    public static QDERecipe getQDERecipeFromInput(ItemStack input) {
        if (input == null) return null;
        for (QDERecipe r : QDERecipes) {
            if (r.getInputItem() == input) return r;
        }
        return null;
    }
}
