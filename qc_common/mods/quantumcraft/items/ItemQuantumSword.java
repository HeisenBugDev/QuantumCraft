package mods.quantumcraft.items;

import mods.quantumcraft.core.interfaces.IQEnergizable;
import net.minecraft.item.ItemStack;

public class ItemQuantumSword extends ItemBase implements IQEnergizable {

    public ItemQuantumSword(int id) {
        super(id);
        this.setMaxDamage(maxQenergyValue);

        this.maxStackSize = 1;
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
    public int setCurrentQEnergyBuffer(ItemStack itemStack, int value) {
        if (value < 0) value = 0;
        if (value > getMaxQEnergyValue(itemStack)) value = getMaxQEnergyValue(itemStack);
        itemStack.setItemDamage(getMaxQEnergyValue(itemStack)-value);
        return value;
    }
}
