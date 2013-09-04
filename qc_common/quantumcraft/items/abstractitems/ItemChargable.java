package quantumcraft.items.abstractitems;

import quantumcraft.core.interfaces.IQEnergizable;
import quantumcraft.items.ItemBase;
import quantumcraft.util.ItemEnergyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemChargable extends ItemBase implements IQEnergizable {

    /**
     * @param id itemID
     * @param maxenergy max energy this chargeable item can hold
     * @param displayEnergy display energy on item (damage bar + tooltip) (DEFAULT IS TRUE)
     */
    public ItemChargable(int id, int maxenergy, boolean displayEnergy) {
        super(id);
        this.setMaxDamage(maxenergy);
        this.maxenergy = maxenergy;
        this.displayEnergy = displayEnergy;
    }

    public ItemChargable(int id, int maxenergy) {
        super(id);
        this.maxenergy = maxenergy;
        this.displayEnergy = true;
    }

    int maxenergy;
    boolean displayEnergy;

    @Override
    public boolean isDamageable() {
        return displayEnergy;
    }

    @Override
    public int getMaxQEnergyValue(ItemStack itemStack) {
        return maxenergy;
    }

    @Override
    public int getCurrentQEnergyBuffer(ItemStack itemStack) {
        return ItemEnergyUtils.getEnergy(itemStack);
    }

    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag) {
        if (itemstack != null && displayEnergy) {
            list.add("QEU: " + this.getCurrentQEnergyBuffer(itemstack) + " / " + this.getMaxQEnergyValue(itemstack));
            ItemEnergyUtils.setDamage(itemstack, this.getCurrentQEnergyBuffer(itemstack), this.maxenergy);
        }
        super.addInformation(itemstack, player, list, flag);
    }

    @Override
    public int setCurrentQEnergyBuffer(ItemStack itemStack, int value) {
        if (value < 0) value = 0;
        if (value > getMaxQEnergyValue(itemStack)) value = getMaxQEnergyValue(itemStack);
        ItemEnergyUtils.setEnergy(itemStack, value);
        return value;
    }
}
