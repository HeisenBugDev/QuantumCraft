package quantumcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBase extends Item {

    private int _metaMax = 0;

    protected void setMetaMax(int max) {
        _metaMax = max;
    }

    public int getMetaMax() {
        return _metaMax;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void getSubItems(Item item, CreativeTabs creativeTab, List subTypes) {
        for (int meta = 0; meta <= _metaMax; meta++) {
            subTypes.add(new ItemStack(item, 1, meta));
        }
    }
}
