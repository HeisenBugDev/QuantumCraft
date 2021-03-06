package quantumcraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemLocationCard extends ItemBase {

    public ItemLocationCard() {
        this.setMaxStackSize(1);
    }

    public boolean getShareTag() {
        return true;
    }

    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString());
    }

    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemstack, EntityPlayer player,
                               List list, boolean flag) {
        if (itemstack != null) {
            if (itemstack.getTagCompound() == null) {
                list.add("Shift right click on a block");
                list.add("NO DATA (THIS IS NOT SUPPOSED TO HAPPEN)");
                return;
            }
            list.add("Put in a crafting menu to clear");
            list.add("x: " + itemstack.getTagCompound().getCompoundTag("LOC").getInteger("x"));
            list.add("y: " + itemstack.getTagCompound().getCompoundTag("LOC").getInteger("y"));
            list.add("z: " + itemstack.getTagCompound().getCompoundTag("LOC").getInteger("z"));
        }

        super.addInformation(itemstack, player, list, flag);
    }

}
