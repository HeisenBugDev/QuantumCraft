package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import quantumcraft.core.Loader;
import quantumcraft.inventory.abstractinv.ContainerBase;
import quantumcraft.tile.TileQDislocator;

public class ContainerQDislocator extends ContainerBase {

    public TileQDislocator tile;

    public ContainerQDislocator(InventoryPlayer ip, TileQDislocator te) {
        super(ip);
        tile = te;
        this.addSlotToContainer(new SlotCustomInput(tile, 0, 20, 34, Loader.ItemLocationCard));
            this.addSlotToContainer(new SlotCustomInput(tile, 1, 131, 34, Loader.ItemLocationCard));
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tile.isUseableByPlayer(par1EntityPlayer);
    }

}
