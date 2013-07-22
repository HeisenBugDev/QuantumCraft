package mods.quantumcraft.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class SlotCustomInput extends Slot {

    private List<ItemStack> itemList;

    public SlotCustomInput(IInventory par1iInventory, int par2, int par3,
                           int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    public SlotCustomInput(IInventory par1iInventory, int par2, int par3,
                           int par4, ItemStack i) {
        super(par1iInventory, par2, par3, par4);
        itemList.add(i);
    }

    public SlotCustomInput addItem(ItemStack i) {
        itemList.add(i);
        return this;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        return itemList.contains(par1ItemStack);
    }

}
