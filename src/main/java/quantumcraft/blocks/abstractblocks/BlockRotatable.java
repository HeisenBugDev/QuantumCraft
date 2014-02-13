package quantumcraft.blocks.abstractblocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import quantumcraft.tile.abstracttiles.TileMachineBase;

public abstract class BlockRotatable extends BlockContainer {


    public BlockRotatable(int id, Material material) {
        super(material);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack stack) {
        if (entity == null) {
            return;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (stack.getTagCompound() != null) {
            stack.getTagCompound().setInteger("x", x);
            stack.getTagCompound().setInteger("y", y);
            stack.getTagCompound().setInteger("z", z);
            te.readFromNBT(stack.getTagCompound());
        }

        if (te instanceof TileMachineBase
                && ((TileMachineBase) te).canRotate()) {
            int facing = MathHelper
                    .floor_double((entity.rotationYaw * 4F) / 360F + 0.5D) & 3;
            if (facing == 0) {
                ((TileMachineBase) te).rotateDirectlyTo(3);
            } else if (facing == 1) {
                ((TileMachineBase) te).rotateDirectlyTo(4);
            } else if (facing == 2) {
                ((TileMachineBase) te).rotateDirectlyTo(2);
            } else if (facing == 3) {
                ((TileMachineBase) te).rotateDirectlyTo(5);
            }
        }
    }

}