package mods.quantumcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import java.util.List;

public class ItemBase extends Item {

    public ItemBase(int id) {
        super(id);
    }

    private int _metaMax = 0;

    @Override
    public Icon getIcon(ItemStack stack, int pass) {
        return null;
    }

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
