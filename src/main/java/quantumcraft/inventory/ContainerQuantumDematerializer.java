package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import quantumcraft.inventory.abstractinv.ContainerUpdatedBase;
import quantumcraft.tile.TileQuantumDematerializer;

public class ContainerQuantumDematerializer extends ContainerUpdatedBase {

    public TileQuantumDematerializer tile;

    public ContainerQuantumDematerializer(InventoryPlayer ip, TileQuantumDematerializer te) {
        super(ip);
        tile = te;
        this.addSlotToContainer(new Slot(tile, 0, 40, 60));
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tile.isUseableByPlayer(par1EntityPlayer);
    }

}
