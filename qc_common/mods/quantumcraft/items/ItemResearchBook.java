package mods.quantumcraft.items;

import mods.quantumcraft.core.Config;
import mods.quantumcraft.core.QuantumCraft;
import mods.quantumcraft.research.ResearchIcons;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemResearchBook extends ItemBase {

    public ItemResearchBook(int par1) {
        super(par1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.openGui(QuantumCraft.instance, 0, world, 0, 0, 0);

        return itemStack;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        ResearchIcons.test = par1IconRegister.registerIcon(Config.NameRIconTest);
    }

}
