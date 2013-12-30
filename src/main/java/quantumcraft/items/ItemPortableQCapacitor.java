package quantumcraft.items;

import quantumcraft.items.abstractitems.ItemChargable;

public class ItemPortableQCapacitor extends ItemChargable {
    public ItemPortableQCapacitor(int id) {
        super(id, 100000);
        this.setMaxStackSize(1);
    }
}
