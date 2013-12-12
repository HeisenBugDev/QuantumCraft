package quantumcraft.inventory.abstractinv;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.SlotArmor;

import java.util.ArrayList;

public abstract class ContainerUpdatedBase extends Container {

    protected ArrayList<Integer> slots = new ArrayList<Integer>();

    public ContainerUpdatedBase(InventoryPlayer ip) {
        int i;
        slots.add(40);
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(ip, j + i * 9 + 9, 8 + j * 18 + 10, 84 + i * 18 + 16));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(ip, i, 8 + i * 18 + 10, 142 + 16));
        }


        this.addSlotToContainer(new SlotArmor(ip, 39, 186, 102, 0));
        this.addSlotToContainer(new SlotArmor(ip, 38, 186, 102 + 18, 1));
        this.addSlotToContainer(new SlotArmor(ip, 37, 186, 102 + 18 * 2, 2));
        this.addSlotToContainer(new SlotArmor(ip, 36, 186, 102 + 18 * 3, 3));
    }

    public int maxStackSize = 64;
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);
        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack() && slotObject.getStack().getMaxStackSize() <=
                maxStackSize) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            boolean canMerge = false;
            //merges the item into player inventory since its in the tileEntity
            if (slot > 39) {
                if (!this.mergeItemStack(stackInSlot, 0, 35, true)) {
                    return null;
                }
            } else {
                for (int aSlot : slots) {
                    if (this.mergeItemStack(stackInSlot, aSlot, aSlot + 1, false)) {
                        canMerge = true;
                    }
                }
                if (!canMerge) {
                    return null;
                }
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize) {
                return null;
            }
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
    }

}
