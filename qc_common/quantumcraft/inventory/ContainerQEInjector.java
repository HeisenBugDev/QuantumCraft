package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.abstractinv.ContainerUpdatedBase;
import quantumcraft.tile.TileQEInjector;

public class ContainerQEInjector extends ContainerUpdatedBase {

    public TileQEInjector tile;

    public ContainerQEInjector(InventoryPlayer ip, TileQEInjector te) {
        super(ip);
        tile = te;
        this.addSlotToContainer(new Slot(tile, 0, 40, 60));
        this.addSlotToContainer(new SlotOutput(tile, 1, 75, 60));
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tile.isUseableByPlayer(par1EntityPlayer);
    }

}
