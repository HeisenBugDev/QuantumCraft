package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.abstractinv.ContainerBase;
import quantumcraft.tile.TileQDematerializer;

public class ContainerQDematerializer extends ContainerBase {

    public TileQDematerializer tile;

    public ContainerQDematerializer(InventoryPlayer ip, TileQDematerializer te) {
        super(ip);
        tile = te;
        this.addSlotToContainer(new Slot(tile, 0, 52, 34));
        int i;

    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tile.isUseableByPlayer(par1EntityPlayer);
    }

}
