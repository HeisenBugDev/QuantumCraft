package mods.quantumcraft.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBase extends Item {

	
	public ItemBase(int id) {
		super(id);
	}

	private int _metaMax = 0;

	protected void setMetaMax(int max) {
		_metaMax = max;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes) {
		for (int meta = 0; meta <= _metaMax; meta++) {
			subTypes.add(new ItemStack(itemId, 1, meta));
		}
	}
}
