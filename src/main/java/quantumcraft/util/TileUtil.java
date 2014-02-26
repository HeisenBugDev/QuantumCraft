package quantumcraft.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileUtil {

    public static void readInventory(NBTTagCompound NBTTagCompound, ItemStack[] inventory){
        NBTTagList nbttaglist = NBTTagCompound.getTagList("Items", 9);

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < inventory.length) {
                inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    public static void saveInventory(NBTTagCompound NBTTagCompound, ItemStack[] inventory) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < inventory.length; ++i) {
            if (inventory[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        NBTTagCompound.setTag("Items", nbttaglist);
    }

    /**
     * @param i         slot
     * @param j         amount to remove
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