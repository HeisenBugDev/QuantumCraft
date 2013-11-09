package quantumcraft.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.LinkedList;

public class SlotCustomInput extends Slot {

    private LinkedList<Item> itemList = new LinkedList<Item>();

    public SlotCustomInput(IInventory par1iInventory, int par2, int par3,
                           int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    public SlotCustomInput(IInventory par1iInventory, int par2, int par3,
                           int par4, Item i) {
        super(par1iInventory, par2, par3, par4);
        itemList.add(i);
    }

    public SlotCustomInput addItem(Item i) {
        itemList.add(i);
        return this;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        for (Item i : itemList) {
            if (i.itemID == par1ItemStack.getItem().itemID) return true;

        }
        return false;
    }

}
