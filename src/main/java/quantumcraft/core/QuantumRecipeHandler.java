package quantumcraft.core;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.item.ItemStack;

import java.util.LinkedList;

public class QuantumRecipeHandler {

    private static LinkedList<QuantumDeenergizerRecipe> QuantumDeenergizerRecipes = new LinkedList<QuantumDeenergizerRecipe>();

    public static void addQDERecipe(QuantumDeenergizerRecipe r) {
        if (QuantumDeenergizerRecipes == null) {
            FMLLog.severe("QDE Recipes list seems to be null! NOT ADDING");
        } else {
            QuantumDeenergizerRecipes.add(r);
        }
    }

    public static QuantumDeenergizerRecipe getQDERecipeFromInput(ItemStack input) {
        if (input == null) return null;
        for (QuantumDeenergizerRecipe r : QuantumDeenergizerRecipes) {
            if (r.getInputItem() == input) return r;
        }
        return null;
    }
}
