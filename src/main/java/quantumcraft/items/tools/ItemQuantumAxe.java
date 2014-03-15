package quantumcraft.items.tools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import quantumcraft.core.Loader;
import quantumcraft.core.interfaces.IQuantumEnergizable;
import quantumcraft.util.ItemEnergyUtils;

public class ItemQuantumAxe extends ItemAxe implements IQuantumEnergizable {

    int maxQenergyValue = 10000;

    public ItemQuantumAxe() {
        super(Loader.ToolMaterials.QUANTUMTOOL);
        this.setMaxDamage(maxQenergyValue + 1);
    }

    @Override
    public int getMaxQEnergyValue() {
        return maxQenergyValue;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public int getCurrentQEnergyBuffer(ItemStack itemStack) {
        return ItemEnergyUtils.getEnergy(itemStack);
    }

    @Override
    public int setCurrentQEnergyBuffer(ItemStack itemStack, int value) {
        return ToolHelper.setCurrentQEnergyBuffer(itemStack, value, this);
    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return false;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_,
                                    int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
        return ToolHelper.onBlockDestroyed(p_150894_1_, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_,
                p_150894_7_, this);
    }


    @Override
    public float getDigSpeed(ItemStack par1ItemStack, Block par2Block, int i) {
        if (getCurrentQEnergyBuffer(par1ItemStack) >= 50) {
            if (!(par2Block.getMaterial() == Material.ground || par2Block.getMaterial() == Material.grass ||
                    par2Block.getMaterial() == Material.sand)) {
                return (par2Block.getMaterial() == Material.wood || par2Block.getMaterial() == Material.plants ||
                        par2Block.getMaterial() == Material.vine) ? this.efficiencyOnProperMaterial :
                        super.getDigSpeed(par1ItemStack, par2Block, i);
            } else return 0.001F;
        } else {
            ItemEnergyUtils.emptyEnergy(par1ItemStack, maxQenergyValue);
            return 0.01F;
        }


    }


}
