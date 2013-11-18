package quantumcraft.inventory.abstractinv;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import quantumcraft.inventory.SlotArmor;
import quantumcraft.inventory.SlotOutput;

public abstract class ContainerUpdatedBase extends Container {

    public ContainerUpdatedBase(InventoryPlayer ip) {
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(ip, j + i * 9 + 9, 8 + j * 18 + 10, 84 + i * 18 + 16));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(ip, i, 8 + i * 18 + 10, 142 + 16));
        }


        this.addSlotToContainer(new SlotArmor(ip, 39, 186, 102, 0));
        this.addSlotToContainer(new SlotArmor(ip, 38, 186, 102 + 18, 1));
        this.addSlotToContainer(new SlotArmor(ip, 37, 186, 102 + 18 * 2, 2));
        this.addSlotToContainer(new SlotArmor(ip, 36, 186, 102 + 18 * 3, 3));
    }

}
