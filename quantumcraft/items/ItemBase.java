package mods.quantumcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBase extends Item {

    public ItemBase(int id) {
        super(id);
    }

    private int _metaMax = 0;

    protected void setMetaMax(int max) {
        _metaMax = max;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes) {
        for (int meta = 0; meta <= _metaMax; meta++) {
            subTypes.add(new ItemStack(itemId, 1, meta));
        }
    }

    public void addInformation(ItemStack itemstack, EntityPlayer player,
                               List list, boolean flag) {
        if (itemstack != null) {

            list.add("QuantumCraft");

        }

        super.addInformation(itemstack, player, list, flag);
    }
}
