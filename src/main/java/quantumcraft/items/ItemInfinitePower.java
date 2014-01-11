package quantumcraft.items;

import net.minecraft.item.ItemStack;
import quantumcraft.items.abstractitems.ItemChargable;

public class ItemInfinitePower extends ItemChargable{
    public ItemInfinitePower(int id) {
        super(id, 100000);
        this.setMaxStackSize(1);
    }

    @Override
    public int setCurrentQEnergyBuffer(ItemStack itemStack, int value) {
        return 100000;
    }

    @Override
    public int getCurrentQEnergyBuffer(ItemStack itemStack) {
        return 100000;
    }
}
