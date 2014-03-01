package quantumcraft.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import quantumcraft.core.Loader;
import quantumcraft.core.interfaces.IQuantumEnergizable;
import quantumcraft.util.ItemEnergyUtils;

public class ItemQuantumSword extends ItemSword implements IQuantumEnergizable {

    int maxQenergyValue = 10000;

    public ItemQuantumSword() {
        super(Loader.ToolMaterials.QUANTUMTOOL);
        this.setMaxDamage(maxQenergyValue + 1);
    }

    @Override
    public int getMaxQEnergyValue() {
        return maxQenergyValue;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public int getCurrentQEnergyBuffer(ItemStack itemStack) {
        return ItemEnergyUtils.getEnergy(itemStack);

    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return false;
    }

    @Override
    public int setCurrentQEnergyBuffer(ItemStack itemStack, int value) {
        return ToolHelper.setCurrentQEnergyBuffer(itemStack, value, this);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3, int par4, int par5, int par6,
                                    EntityLivingBase par7EntityLivingBase) {
        if (getCurrentQEnergyBuffer(par1ItemStack) < 20) return false;
        if ((double) par2World.getBlock(par4, par5, par6).getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
            setCurrentQEnergyBuffer(par1ItemStack, getCurrentQEnergyBuffer(par1ItemStack) - 20);
            ItemEnergyUtils.setDamage(par1ItemStack, getCurrentQEnergyBuffer(par1ItemStack), maxQenergyValue);
        }

        return true;
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (getCurrentQEnergyBuffer(stack) >= 50) {
            setCurrentQEnergyBuffer(stack, getCurrentQEnergyBuffer(stack) - 1);
            ItemEnergyUtils.setDamage(stack, getCurrentQEnergyBuffer(stack), maxQenergyValue);
            return false;
        } else {
            ItemEnergyUtils.emptyEnergy(stack, maxQenergyValue);
            return true;
        }
    }

}
