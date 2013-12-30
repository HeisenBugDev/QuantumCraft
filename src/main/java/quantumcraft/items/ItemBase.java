package quantumcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBase extends Item {

    private int _metaMax = 0;

    public ItemBase(int id) {
        super(id);
    }

    protected void setMetaMax(int max) {
        _metaMax = max;
    }

    public int getMetaMax() {
        return _metaMax;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes) {
        for (int meta = 0; meta <= _metaMax; meta++) {
            subTypes.add(new ItemStack(itemId, 1, meta));
        }
    }
}
