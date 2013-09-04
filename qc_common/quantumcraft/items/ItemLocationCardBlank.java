package quantumcraft.items;

import cpw.mods.fml.common.FMLLog;
import quantumcraft.core.Loader;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemLocationCardBlank extends ItemBase {

    public ItemLocationCardBlank(int id) {
        super(id);
        this.setMaxStackSize(1);
    }

    public boolean getShareTag() {
        return true;
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString());
    }

    public void addInformation(ItemStack itemstack, EntityPlayer player,
                               List list, boolean flag) {
        list.add("Shift right click on a block");
        list.add("NO DATA");
        super.addInformation(itemstack, player, list, flag);
    }


    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
                             int par5, int par6, int par7, float par8, float par9, float par10) {

        if (par2EntityPlayer.isSneaking() && !par1ItemStack.hasTagCompound()) {
            FMLLog.fine("Location card just got S-R-CLICKED");

            //ISSUE #27
            par1ItemStack.itemID = Loader.ItemLocationCard.itemID;

            NBTTagCompound t = new NBTTagCompound();
            t.setInteger("x", par4);
            t.setInteger("y", par5);
            t.setInteger("z", par6);
            NBTTagCompound pt = par1ItemStack.getTagCompound();
            if (pt == null) {
                par1ItemStack.setTagCompound(new NBTTagCompound());
                pt = par1ItemStack.getTagCompound();
            }
            pt.setCompoundTag("LOC", t);
            par1ItemStack.setTagCompound(pt);
        }
        return true;
    }

}
