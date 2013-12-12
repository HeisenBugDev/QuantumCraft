package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.abstractinv.ContainerBase;
import quantumcraft.inventory.abstractinv.ContainerUpdatedBase;
import quantumcraft.tile.TileIONForge;

public class ContainerIONForge extends ContainerUpdatedBase {

    public TileIONForge tile;

    public ContainerIONForge(InventoryPlayer ip, TileIONForge te) {
        super(ip);
        tile = te;
        slots.add(42);
        this.addSlotToContainer(new Slot(tile, 0, 40, 48));
        this.addSlotToContainer(new SlotOutput(tile, 1, 75, 48));
        this.addSlotToContainer(new Slot(tile, 2, 40, 76));
        this.addSlotToContainer(new SlotOutput(tile, 3, 75, 76));
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.tile.isUseableByPlayer(entityplayer);
    }
}
