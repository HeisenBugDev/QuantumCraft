package quantumcraft.items;

import quantumcraft.core.Config;
import quantumcraft.core.QuantumCraft;
import quantumcraft.research.ResearchIcons;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
    public void registerIcons(IconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);
        ResearchIcons.test = par1IconRegister.registerIcon(Config.NameRIconTest);
    }

}
