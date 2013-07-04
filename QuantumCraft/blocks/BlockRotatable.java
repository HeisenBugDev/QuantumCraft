package mods.quantumcraft.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class BlockRotatable extends BlockContainer implements IRotatable {
 
    public BlockRotatable(int id, Material material) {
        super(id, material);
    }
 
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLivingBase, ItemStack itemStack) {
        int angle = MathHelper.floor_double((entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int change = 3;
 
        switch (angle) {
            case 0:
                change = 2;
                break;
            case 1:
                change = 5;
                break;
            case 2:
                change = 3;
                break;
            case 3:
                change = 4;
                break;
        }
 
        setDirection(world, x, y, z, ForgeDirection.getOrientation(change));
    }
 
    @Override
    public ForgeDirection getDirection(IBlockAccess world, int x, int y, int z) {
        return ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
    }
 
    @Override
    public void setDirection(World world, int x, int y, int z, ForgeDirection facingDirection) {
        world.setBlockMetadataWithNotify(x, y, z, facingDirection.ordinal(), 3);
    }
}