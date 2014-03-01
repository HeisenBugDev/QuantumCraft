package quantumcraft.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import quantumcraft.core.Loader;
import quantumcraft.core.interfaces.IQuantumEnergizable;
import quantumcraft.util.ItemEnergyUtils;

public class ItemQuantumShovel extends ItemSpade implements IQuantumEnergizable {

    int maxQenergyValue = 10000;

    public ItemQuantumShovel() {
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
        return ToolHelper
                .onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLivingBase, this);
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if (getCurrentQEnergyBuffer(stack) >= 50) {
            return 10.0F;
        } else {
            ItemEnergyUtils.emptyEnergy(stack, maxQenergyValue);
            return 0.01F;
        }
    }
}
