package quantumcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import quantumcraft.core.Config;
import quantumcraft.core.interfaces.IUpgrade;

public class ItemUpgrade extends ItemBase implements IUpgrade {

    public IIcon[] icons = new IIcon[2];
    public String[] names = {"Upgrade Template", "Overclock Upgrade"};

    public ItemUpgrade() {
        this.setMetaMax(1);
        this.setMaxStackSize(
                1);   //THIS IS SUPPOSED TO BE 16 BUT DIFFERENT UPGRADES STACK INTO ONE WHEN PICKED UP. <= You must register the in the loader separately!
    }

    @Override
    public IIcon getIconFromDamage(int par1) {
        return icons[par1];
    }

    @Override
    public String getItemStackDisplayName(ItemStack par1ItemStack) {
        return names[par1ItemStack.getItemDamage()];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i <= this.getMetaMax(); i++) {
            icons[i] = par1IconRegister.registerIcon(Config.getTextureName(Config.NameItemUpgrade[i]));
        }
    }

    @Override
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        return true;
    }

}
