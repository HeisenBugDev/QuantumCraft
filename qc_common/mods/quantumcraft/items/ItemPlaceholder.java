package mods.quantumcraft.items;

import net.minecraft.util.Icon;

public class ItemPlaceholder extends ItemBase {

    public ItemPlaceholder(int id) {
        super(id);
    }

    public void setTexture(Icon i)
    {
        this.itemIcon = i;
    }
}
