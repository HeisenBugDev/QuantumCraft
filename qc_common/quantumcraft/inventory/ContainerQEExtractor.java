package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.abstractinv.ContainerBase;
import quantumcraft.tile.TileQEExtractor;

public class ContainerQEExtractor extends ContainerBase {

    public TileQEExtractor tile;

    public ContainerQEExtractor(InventoryPlayer ip, TileQEExtractor te) {
        super(ip);
        tile = te;
        this.addSlotToContainer(new Slot(tile, 0, 20, 34));
        this.addSlotToContainer(new SlotOutput(tile, 1, 131, 34));
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tile.isUseableByPlayer(par1EntityPlayer);
    }

}
