package quantumcraft.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

public class ItemEnergyUtils {
    /**
     * RETURNS THE QUANTUM ENERGY VALUE IN ITEMSTACK
     *
     * @param is INPUT ITEMSTACK
     * @return CURRENT ENERGY VALUE
     */
    public static int getEnergy(ItemStack is) {
        if (is.getTagCompound() == null) {
            return 0;
        }
        NBTTagCompound IS = is.getTagCompound();
        return IS.getCompoundTag("QEV").getInteger("VALUE");
    }

    /**
     * SETS CURRENT QUANTUM ENERGY VALUE IN ITEMSTACK
     *
     * @param is INPUT ITESTACK
     * @param e  ENERGY
     */
    public static void setEnergy(ItemStack is, int e) {
        NBTTagCompound QEV = new NBTTagCompound();
        QEV.setInteger("VALUE", e);
        NBTTagCompound IS = is.getTagCompound();
        if (IS == null) {
            IS = new NBTTagCompound();
        }
        IS.setTag("QEV", QEV);
        is.setTagCompound(IS);
    }

    public static int getDamageFromEnergy(int currentEnergy, int maxEnergy) {
        return currentEnergy / maxEnergy * 31;
    }

    public static void setDamage(ItemStack is, int currentEnergy, int maxEnergy) {
        is.setItemDamage(maxEnergy - currentEnergy);
    }

    public static void emptyEnergy(ItemStack is, int maxEnergy) {
        is.setItemDamage(maxEnergy);
    }

}
