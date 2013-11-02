package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.abstractinv.ContainerBase;
import quantumcraft.tile.TileIONForge;

public class ContainerIONForge extends ContainerBase {
    public TileIONForge tile;

    public ContainerIONForge(InventoryPlayer ip, TileIONForge te) {
        super(ip);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.tile.isUseableByPlayer(par1EntityPlayer);
    }
}
