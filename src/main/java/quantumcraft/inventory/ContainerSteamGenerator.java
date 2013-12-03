package quantumcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import quantumcraft.inventory.abstractinv.ContainerUpdatedBase;

public class ContainerSteamGenerator extends ContainerUpdatedBase {
    public ContainerSteamGenerator(InventoryPlayer ip) {
        super(ip);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return false;
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot){
        return null;
    }
}
