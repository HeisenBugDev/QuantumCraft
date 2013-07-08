package mods.quantumcraft.items;

import mods.quantumcraft.core.interfaces.IMultiTool;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class ItemMultiTool extends ItemBase implements IMultiTool {

    public ItemMultiTool(int i) {
        super(i);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
                                  World world, int x, int y, int z, int side, float hitX, float hitY,
                                  float hitZ) {
        Block block = Block.blocksList[world.getBlockId(x, y, z)];
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
    public boolean shouldPassSneakingClickToBlock(World world, int x, int y,
                                                  int z) {
        return true;
    }

    @Override
    public boolean isActive(ItemStack stack) {
        return false;
    }

}
