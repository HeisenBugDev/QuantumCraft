package mods.quantumcraft.items;

import cpw.mods.fml.common.FMLLog;
import mods.quantumcraft.core.Config;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemLocationCard extends ItemBase {

    private Icon iconBlank;

    public ItemLocationCard(int id) {
        super(id);
        this.setMaxStackSize(1);
    }

    public boolean getShareTag() {
        return true;
    }

    public String getItemStackDisplayName(ItemStack par1ItemStack)
    {
        String e = par1ItemStack.hasTagCompound() ? ": Filled" : ": Blank";
        return StatCollector.translateToLocal(this.getUnlocalizedName(par1ItemStack) + ".name") + e;
    }

    public Icon getIcon(ItemStack stack, int pass)
    {
        return stack.hasTagCompound() ? this.itemIcon : this.iconBlank;
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        this.iconBlank = par1IconRegister.registerIcon(Config.NameItemLocationCardBlank);
    }

    public void addInformation(ItemStack itemstack, EntityPlayer player,
                               List list, boolean flag) {
        if (itemstack != null) {

            if (itemstack.getTagCompound() == null) {
                list.add("Shift r-click on a block");
                list.add("Blank");
                return;
            }
            list.add("Put in a crafting menu to clear");
            list.add("x: " + itemstack.getTagCompound().getCompoundTag("LOC").getInteger("x"));
            list.add("y: " + itemstack.getTagCompound().getCompoundTag("LOC").getInteger("y"));
            list.add("z: " + itemstack.getTagCompound().getCompoundTag("LOC").getInteger("z"));
        }

        super.addInformation(itemstack, player, list, flag);
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
                             int par5, int par6, int par7, float par8, float par9, float par10) {

        if (par2EntityPlayer.isSneaking() && !par1ItemStack.hasTagCompound()) {
            FMLLog.fine("Location card just got S-R-CLICKED");

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
