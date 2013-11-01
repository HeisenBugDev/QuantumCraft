package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import quantumcraft.tile.TileQDeenergizer;

public class ContainerQDeenergizer extends Container {

    public TileQDeenergizer tile;

    public ContainerQDeenergizer(InventoryPlayer ip, TileQDeenergizer te) {
        tile = te;
        this.addSlotToContainer(new Slot(tile, 0, 20, 34));
        this.addSlotToContainer(new SlotOutput(tile, 1, 131, 34));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(ip, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(ip, i, 8 + i * 18, 142));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tile.isUseableByPlayer(par1EntityPlayer);
    }

}
