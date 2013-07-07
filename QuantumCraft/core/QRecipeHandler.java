package mods.quantumcraft.core;

import java.util.Collection;
import java.util.List;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.item.ItemStack;

public class QRecipeHandler {

	private static QDERecipe[] QDERecipes = new QDERecipe[64];
	static int i = 0;
	public static void addQDERecipe(QDERecipe r)
	{
		if (QDERecipes == null) FMLLog.severe("QDE Recipes list seeems to be null!");
		QDERecipes[i] = r;
		i++;
	}
	
	public static QDERecipe getQDERecipeFromInput(ItemStack input)
	{
		if (input == null) return null;
		for (QDERecipe r : QDERecipes)
		{
			if (r.getInputItem().itemID == input.itemID) return r;
		}
		return null;
	}

	
	
}
