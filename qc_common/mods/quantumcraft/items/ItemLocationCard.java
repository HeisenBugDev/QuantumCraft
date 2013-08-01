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
    private Icon iconFilled;
    public ItemLocationCard(int id) {
        super(id);
        this.setMaxStackSize(1);
    }

    public boolean getShareTag() {
        return true;
    }

    public Icon getIconFromDamage(int par1)
    {
        switch(par1) {
            case 0:
                return this.iconBlank;
            case 1:
                return this.iconFilled;
            default:
                return this.itemIcon;
        }
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconFilled = par1IconRegister.registerIcon(Config.getTextureName(Config.NameItemLocationCard));
        this.iconBlank = par1IconRegister.registerIcon(Config.getTextureName(Config.NameItemLocationCardBlank));
        this.itemIcon = iconBlank;
    }

    public void addInformation(ItemStack itemstack, EntityPlayer player,
                               List list, boolean flag) {
        if (itemstack != null) {
            if (itemstack.getTagCompound() == null) {
                list.add("Shift right click on a block");
                list.add("NO DATA");
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

            //ISSUE #27
            par1ItemStack.setItemDamage(1);

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
