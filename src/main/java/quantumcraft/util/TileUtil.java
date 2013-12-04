package quantumcraft.util;

import net.minecraft.item.ItemStack;

public class TileUtil {
    /**
     * @param i slot
     * @param j amount to remove
     * @param inventory inventory array of itemstacks
     * @return the itemstack that has been modified
     */
    public static ItemStack decrStackSize(int i, int j, ItemStack[] inventory) {
        if (inventory[i] != null) {
            ItemStack itemstack;

            if (inventory[i].stackSize <= j) {
                itemstack = inventory[i];
                inventory[i] = null;
                return itemstack;
            } else {
                itemstack = inventory[i].splitStack(j);

                if (inventory[i].stackSize == 0) {
                    inventory[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }
}