package mods.quantumcraft.core;

import mods.quantumcraft.core.interfaces.IMultiTool;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class Util {
    public static boolean isHoldingWrench(EntityPlayer player) {
        if (player.inventory.getCurrentItem() == null) {
            return false;
        }
        Item currentItem = Item.itemsList[player.inventory.getCurrentItem().itemID];
        if (currentItem instanceof IMultiTool) {
            return ((IMultiTool) currentItem).isActive(player.inventory
                    .getCurrentItem());
        } else if (currentItem instanceof IMultiTool) {
            return true;
        }

        return false;
    }
}
