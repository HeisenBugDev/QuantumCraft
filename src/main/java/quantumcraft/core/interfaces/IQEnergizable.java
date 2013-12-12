package quantumcraft.core.interfaces;

import net.minecraft.item.ItemStack;

public interface IQEnergizable {

    /**
     * @return maximum energy the item can hold
     */
    public int getMaxQEnergyValue(ItemStack itemStack);

    /**
     * @return the current energy value the item's got
     */
    public int getCurrentQEnergyBuffer(ItemStack itemStack);

    /**
     * @param value to set the buffer to
     * @return value AFTER the change
     */
    public int setCurrentQEnergyBuffer(ItemStack itemStack, int value);

}
