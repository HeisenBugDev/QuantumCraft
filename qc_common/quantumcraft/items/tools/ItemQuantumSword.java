package quantumcraft.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import quantumcraft.core.Loader;
import quantumcraft.core.interfaces.IQEnergizable;
import quantumcraft.util.ItemEnergyUtils;

public class ItemQuantumSword extends ItemSword implements IQEnergizable {

    int maxQenergyValue = 1000;

    public ItemQuantumSword(int id) {
        super(id, Loader.ToolMaterials.QUANTUMTOOL);
        this.setMaxDamage(maxQenergyValue + 1);
    }

    @Override
    public int getMaxQEnergyValue(ItemStack itemStack) {
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
        if (value < 0) value = 0;
        if (value > getMaxQEnergyValue(itemStack)) value = getMaxQEnergyValue(itemStack);
        ItemEnergyUtils.setEnergy(itemStack, value);
        return value;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6,
                                    EntityLivingBase par7EntityLivingBase) {
        if ((double) Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
            if (getCurrentQEnergyBuffer(par1ItemStack) > 0) {
                setCurrentQEnergyBuffer(par1ItemStack, getCurrentQEnergyBuffer(par1ItemStack) - 1);
                ItemEnergyUtils.setDamage(par1ItemStack, getCurrentQEnergyBuffer(par1ItemStack), maxQenergyValue);
            }
        }

        return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (getCurrentQEnergyBuffer(stack) > 0) {
            setCurrentQEnergyBuffer(stack, getCurrentQEnergyBuffer(stack) - 1);
            ItemEnergyUtils.setDamage(stack, getCurrentQEnergyBuffer(stack), maxQenergyValue);
            return false;
        } else {
            ItemEnergyUtils.emptyEnergy(stack, maxQenergyValue);
            return true;
        }
    }

}
