package quantumcraft.inventory;

import quantumcraft.tile.TileQEInjector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerQEInjector extends Container {

    public TileQEInjector tile;

    public ContainerQEInjector(InventoryPlayer ip, TileQEInjector te) {
        tile = te;
        this.addSlotToContainer(new Slot(tile, 0, 40, 60));
        this.addSlotToContainer(new SlotOutput(tile, 1, 75, 60));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(ip, j + i * 9 + 9, 8 + j * 18+10, 84 + i * 18+16));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(ip, i, 8 + i * 18+10, 142+16));
        }


        this.addSlotToContainer(new SlotArmor(ip, 39, 186, 102, 0));
        this.addSlotToContainer(new SlotArmor(ip, 38, 186, 102+18, 1));
        this.addSlotToContainer(new SlotArmor(ip, 37, 186, 102+18*2, 2));
        this.addSlotToContainer(new SlotArmor(ip, 36, 186, 102+18*3, 3));
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tile.isUseableByPlayer(par1EntityPlayer);
    }

}
