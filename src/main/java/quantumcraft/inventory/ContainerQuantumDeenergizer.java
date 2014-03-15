package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import quantumcraft.inventory.abstractinv.ContainerUpdatedBase;
import quantumcraft.tile.TileQuantumDeenergizer;

public class ContainerQuantumDeenergizer extends ContainerUpdatedBase {

    public TileQuantumDeenergizer tile;
    public ContainerQuantumDeenergizer(InventoryPlayer ip, TileQuantumDeenergizer te) {
        super(ip);
        tile = te;
        this.addSlotToContainer(new Slot(tile, 0, 40, 60));
        this.addSlotToContainer(new SlotOutput(tile, 1, 75, 60));
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tile.isUseableByPlayer(par1EntityPlayer);
    }

}
