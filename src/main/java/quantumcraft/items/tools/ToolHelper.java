package quantumcraft.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import quantumcraft.core.interfaces.IQuantumEnergizable;
import quantumcraft.util.ItemEnergyUtils;

public class ToolHelper {

    public static boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3, int par4, int par5,
                                           int par6, EntityLivingBase par7EntityLivingBase, IQuantumEnergizable item) {
        if (item.getCurrentQEnergyBuffer(par1ItemStack) < 50) return false;
        if ((double) par2World.getBlock(par4, par5, par6).getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
            setCurrentQEnergyBuffer(par1ItemStack, (item.getCurrentQEnergyBuffer(par1ItemStack) - 50), item);
            ItemEnergyUtils
                    .setDamage(par1ItemStack, item.getCurrentQEnergyBuffer(par1ItemStack), item.getMaxQEnergyValue());
        }

        return true;
    }

    public static int setCurrentQEnergyBuffer(ItemStack itemStack, int value, IQuantumEnergizable item) {
        if (value < 0) value = 0;
        if (value > item.getMaxQEnergyValue()) value = item.getMaxQEnergyValue();
        ItemEnergyUtils.setEnergy(itemStack, value);
        return value;
    }
}
