package mods.quantumcraft.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemEnergyUtils {
    /**
     * RETURNS THE QUANTUM ENERGY VALUE IN ITEMSTACK
     * @param is INPUT ITEMSTACK
     * @return CURRENT ENERGY VALUE
     */
    public static int getEnergy(ItemStack is) {
        NBTTagCompound IS = is.getTagCompound();
        return IS.getCompoundTag("QEV").getInteger("VALUE");
    }

    /**
     * SETS CURRENT QUANTUM ENERGY VALUE IN ITEMSTACK
     * @param is INPUT ITESTACK
     * @param e ENERGY
     */
    public static void setEnergy(ItemStack is, int e) {
        NBTTagCompound QEV = new NBTTagCompound("QEV");
        QEV.setInteger("VALUE", e);
        NBTTagCompound IS = is.getTagCompound();
        IS.setCompoundTag("QEV", QEV);
        is.setTagCompound(IS);
    }

    public static int getDamageFromEnergy(int currentEnergy, int maxEnergy) {
        return currentEnergy/maxEnergy*31;
    }
}
