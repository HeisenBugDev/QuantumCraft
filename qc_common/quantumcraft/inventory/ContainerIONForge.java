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
        this.addSlotToContainer(new Slot(tile, 0, 20, 20));
        this.addSlotToContainer(new SlotOutput(tile, 1, 131, 20));
        this.addSlotToContainer(new Slot(tile, 0, 20, 44));
        this.addSlotToContainer(new SlotOutput(tile, 1, 131, 44));
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.tile.isUsableByPlayer(entityplayer);
    }
}
