package mods.quantumcraft.items;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class ItemLocationCard extends Item {

    public ItemLocationCard(int id) {
        super(id);
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        FMLLog.fine("Location card just got R-CLICKED");
        //PUT THE COORDINATE SAVING HERE;
        return true;
    }
}
