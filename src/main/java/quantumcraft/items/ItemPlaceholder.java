package quantumcraft.items;

import net.minecraft.util.IIcon;

public class ItemPlaceholder extends ItemBase {

    public ItemPlaceholder(int id) {
        super(id);
    }

    public void setTexture(IIcon i) {
        this.itemIcon = i;
    }
}
