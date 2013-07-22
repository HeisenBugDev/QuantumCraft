package mods.quantumcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.quantumcraft.core.Loader;
import net.minecraft.client.renderer.texture.IconRegister;
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
