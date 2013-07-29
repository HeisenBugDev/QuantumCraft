package mods.quantumcraft.items;

import mods.quantumcraft.core.Loader;
import mods.quantumcraft.core.interfaces.IQEnergizable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemQuantumSword extends ItemSword implements IQEnergizable {

    public ItemQuantumSword(int id) {
        super(id, Loader.ToolMaterials.QUANTUMTOOL);
        this.setMaxDamage(maxQenergyValue);


    }

    int maxQenergyValue = 1000;

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
        return getMaxQEnergyValue(itemStack)-itemStack.getItemDamage();

    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    @Override
    public int setCurrentQEnergyBuffer(ItemStack itemStack, int value) {
        if (value < 0) value = 0;
        if (value > getMaxQEnergyValue(itemStack)) value = getMaxQEnergyValue(itemStack);
        itemStack.setItemDamage(getMaxQEnergyValue(itemStack)-value);
        return value;
    }
}
