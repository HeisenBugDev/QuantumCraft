package mods.quantumcraft.items.abstractitems;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 8/14/13
 * Time: 3:39 PM
 */
public interface QuantumTool {
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6,
                                    EntityLivingBase par7EntityLivingBase);

    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block);
}
