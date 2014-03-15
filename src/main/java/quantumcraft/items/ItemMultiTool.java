package quantumcraft.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import quantumcraft.core.interfaces.IMultiTool;

public class ItemMultiTool extends ItemBase implements IMultiTool {

    public ItemMultiTool() {
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
                                  World world, int x, int y, int z, int side, float hitX, float hitY,
                                  float hitZ) {
        Block block = world.getBlock(x, y, z);
        if (block != null) {
            if (block.rotateBlock(world, x, y, z,
                    ForgeDirection.getOrientation(side))) {
                player.swingItem();
                return !world.isRemote;
            }
        }
        return false;
    }

    @Override
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        return true;
    }

    @Override
    public boolean isActive(ItemStack stack) {
        return false;
    }

}
