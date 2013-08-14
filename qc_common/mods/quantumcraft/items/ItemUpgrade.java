package mods.quantumcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.quantumcraft.core.Config;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;

public class ItemUpgrade extends ItemBase {
    public ItemUpgrade(int id) {
        super(id);
        this.setMetaMax(1);
        this.setMaxStackSize(1);   //THIS IS SUPPOSED TO BE 16 BUT DIFFERENT UPGRADES STACK INTO ONE WHEN PICKED UP.
    }

    public Icon[] icons = new Icon[2];
    public String[] names = { "Upgrade Template", "Overclock Upgrade" };

    @Override
    public Icon getIconFromDamage(int par1)
    {
        return icons[par1];
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack)
    {
       return names[par1ItemStack.getItemDamage()];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        for (int i = 0; i <= this.getMetaMax(); i++) {
            icons[i] = par1IconRegister.registerIcon(Config.getTextureName(Config.NameItemUpgrade[i]));
        }
    }


}
