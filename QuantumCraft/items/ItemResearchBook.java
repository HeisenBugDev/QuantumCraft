package mods.quantumcraft.items;

import mods.quantumcraft.core.QuantumCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemResearchBook extends Item {

    public ItemResearchBook(int par1) {
        super(par1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.openGui(QuantumCraft.instance, 0, world, 0, 0, 0);

        return itemStack;
    }


}
