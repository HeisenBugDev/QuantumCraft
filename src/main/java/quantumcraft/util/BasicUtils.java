package quantumcraft.util;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import quantumcraft.core.interfaces.IMultiTool;

import java.util.Random;

public class BasicUtils {

    // Cardinal Orientation
    public static final int dirBottom = 0;
    public static final int dirTop = 1;
    public static final int dirEast = 2;
    public static final int dirWest = 3;
    public static final int dirNorth = 4;
    public static final int dirSouth = 5;
    // Axial Orientation
    public static final int dirYNeg = 0;
    public static final int dirYPos = 1;
    public static final int dirZNeg = 2;
    public static final int dirZPos = 3;
    public static final int dirXNeg = 4;
    public static final int dirXPos = 5;
    public static Random rand = new Random();

    public static Block getBlockInstance(IBlockAccess world, int x, int y, int z) {
        return world.getBlock(x, y, z);
    }

    public static Block getBlockAtTarget(DrawBlockHighlightEvent event) {
        return getBlockInstance(event.player.worldObj, event.target.blockX, event.target.blockY, event.target.blockZ);
    }

    public static TileEntity getTileEntityAtTarget(DrawBlockHighlightEvent event) {
        return event.player.getEntityWorld()
                .getTileEntity(event.target.blockX, event.target.blockY, event.target.blockZ);
    }

    public static int overclockMultiplier(int[] uids) {
        int res = 0;
        for (int u : uids) {
            if (u == 1) res++;
        }
        return res;
    }

    public static boolean isRedstonePowered(World world, int x, int y, int z) {
        if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
            return true;
        }
        for (BlockPosition bp : new BlockPosition(x, y, z).getAdjacent(false)) {
            Block block = world.getBlock(bp.x, bp.y, bp.z);
            if (block == Blocks.redstone_wire && block.isProvidingStrongPower(world, bp.x, bp.y, bp.z, 1) > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRedstonePowered(TileEntity te) {
        return isRedstonePowered(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
    }

    public static boolean isServer(World world) {
        return world != null && !world.isRemote;
    }

    public static boolean isNotClient(World world) {
        return world == null || !world.isRemote;
    }

    public static boolean isHoldingWrench(EntityPlayer player) {
        return player.inventory.getCurrentItem() != null &&
                player.inventory.getCurrentItem().getItem() instanceof IMultiTool;
    }

    public static void dropItem(World world, int x, int y, int z, ItemStack itemStack) {
        if (isNotClient(world)) {
            double var5 = 0.7D;
            double var7 = (double) world.rand.nextFloat() * var5 + (1.0D - var5) * 0.5D;
            double var9 = (double) world.rand.nextFloat() * var5 + (1.0D - var5) * 0.5D;
            double var11 = (double) world.rand.nextFloat() * var5 + (1.0D - var5) * 0.5D;
            EntityItem var13 =
                    new EntityItem(world, (double) x + var7, (double) y + var9, (double) z + var11, itemStack);
            var13.delayBeforeCanPickup = 10;
            world.spawnEntityInWorld(var13);
        }
    }

    public static TileEntity getTileEntity(IBlockAccess access, Coords coords, Class clazz) {
        if (access == null) return null;
        TileEntity te = access.getTileEntity(coords.x, coords.y, coords.z);
        return !clazz.isInstance(te) ? null : te;
    }

    /**
     * Pass a single coord int and get the chunk's equivalent one back.
     *
     * @param q Either x or z location
     * @return The chunk coord
     */
    public static int getChunk(int q) {
        return (int) Math.floor(q / 16);
    }

    public static boolean areStacksTheSame(ItemStack is1, ItemStack is2) {
        return !(is1 == null || is2 == null) && is1.getItem() == is2.getItem() &&
                is1.getItemDamage() == is2.getItemDamage();
    }

}