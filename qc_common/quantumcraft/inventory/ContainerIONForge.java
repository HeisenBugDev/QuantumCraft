package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.abstractinv.ContainerBase;
import quantumcraft.tile.TileIONForge;

public class ContainerIONForge extends ContainerBase {

    public TileIONForge tile;

    public ContainerIONForge(InventoryPlayer ip, TileIONForge te) {
        super(ip);
        tile = te;
        this.addSlotToContainer(new Slot(tile, 0, 21, 22));
        this.addSlotToContainer(new SlotOutput(tile, 1, 130, 22));
        this.addSlotToContainer(new Slot(tile, 2, 21, 46));
        this.addSlotToContainer(new SlotOutput(tile, 3, 130, 46));
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);
        System.out.println(slot);
        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            //merges the item into player inventory since its in the tileEntity
            if (slot > 35) {
                if (!this.mergeItemStack(stackInSlot, 0, 35, true)) {
                    return null;
                }
            }
            //places it into the tileEntity is possible since its in the player inventory
            else if (!this.mergeItemStack(stackInSlot, 36, 37, false) && !this.mergeItemStack(stackInSlot, 38, 39, false)) {
                return null;
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

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.tile.isUseableByPlayer(entityplayer);
    }
}
