package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.abstractinv.ContainerUpdatedBase;
import quantumcraft.tile.TileQCapacitor;

public class ContainerQCapacitor extends ContainerUpdatedBase{
    public TileQCapacitor tile;
    public ContainerQCapacitor(InventoryPlayer ip, TileQCapacitor te) {
        super(ip);
        tile = te;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.tile.isUseableByPlayer(entityplayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        return null;
    }
}
