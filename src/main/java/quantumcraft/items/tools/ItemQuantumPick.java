package quantumcraft.items.tools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import quantumcraft.core.Loader;
import quantumcraft.core.interfaces.IQEnergizable;
import quantumcraft.util.ItemEnergyUtils;

public class ItemQuantumPick extends ItemPickaxe implements IQEnergizable {

    int maxQenergyValue = 10000;

    public ItemQuantumPick(int par1) {
        super(par1, Loader.ToolMaterials.QUANTUMTOOL);
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
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6,
                                    EntityLivingBase par7EntityLivingBase) {
        return ToolHelper
                .onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLivingBase, this);
    }

    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        if (getCurrentQEnergyBuffer(par1ItemStack) >= 50) {
            if (!(par2Block.blockMaterial == Material.ground || par2Block.blockMaterial == Material.grass ||
                    par2Block.blockMaterial == Material.sand)) {
                return ((par2Block.blockMaterial == Material.iron || par2Block.blockMaterial == Material.anvil ||
                        par2Block.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial :
                        super.getStrVsBlock(par1ItemStack, par2Block)) + 1F;
            } else return 0.001F;
        } else {
            ItemEnergyUtils.emptyEnergy(par1ItemStack, maxQenergyValue);
            return 0.01F;
        }


    }


}
