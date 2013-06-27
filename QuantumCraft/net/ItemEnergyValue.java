package mods.QuantumCraft.net;

import mods.QuantumCraft.core.Loader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemEnergyValue {
	
	public int getEnergyValue(int ID)
	{
		if (ID == Loader.ItemCrystalQuantonium.itemID) return 100;
		else return 0;
	}
	
	public int getEnergyValue(Item i)
	{
		return getEnergyValue(i.itemID);
	}
	
	public int getEnergyValue(ItemStack i)
	{
		return getEnergyValue(i.itemID);
	}
	
}
