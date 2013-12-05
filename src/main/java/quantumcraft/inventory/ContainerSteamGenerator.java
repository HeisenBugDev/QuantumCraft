package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.abstractinv.ContainerUpdatedBase;
import quantumcraft.tile.TileSteamGenerator;

public class ContainerSteamGenerator extends ContainerUpdatedBase {

    public TileSteamGenerator tile;

    public ContainerSteamGenerator(InventoryPlayer ip, TileSteamGenerator te) {
        super(ip);
        tile = te;
        this.addSlotToContainer(new Slot(tile, 0, 40, 60));
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tile.isUseableByPlayer(entityPlayer);
    }

}
