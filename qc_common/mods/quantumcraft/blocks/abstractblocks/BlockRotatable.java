package mods.quantumcraft.blocks.abstractblocks;

import mods.quantumcraft.machine.TileQDeenergizer;
import mods.quantumcraft.machine.TileQDematerializer;
import mods.quantumcraft.machine.TileQEInjector;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class BlockRotatable extends BlockContainer {


    public BlockRotatable(int id, Material material) {
        super(id, material);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack stack) {
        if (entity == null) {
            return;
        }
        TileEntity te = world.getBlockTileEntity(x, y, z);
        //WTF? -sammko
        if (stack.getTagCompound() != null) {
            stack.getTagCompound().setInteger("x", x);
            stack.getTagCompound().setInteger("y", y);
            stack.getTagCompound().setInteger("z", z);
            te.readFromNBT(stack.getTagCompound());
        }

        if (te instanceof TileQDeenergizer
                && ((TileQDeenergizer) te).canRotate()) {
            int facing = MathHelper
                    .floor_double((entity.rotationYaw * 4F) / 360F + 0.5D) & 3;
            if (facing == 0) {
                ((TileQDeenergizer) te).rotateDirectlyTo(3);
            } else if (facing == 1) {
                ((TileQDeenergizer) te).rotateDirectlyTo(4);
            } else if (facing == 2) {
                ((TileQDeenergizer) te).rotateDirectlyTo(2);
            } else if (facing == 3) {
                ((TileQDeenergizer) te).rotateDirectlyTo(5);
            }
        }

        if (te instanceof TileQEInjector
                && ((TileQEInjector) te).canRotate()) {
            int facing = MathHelper
                    .floor_double((entity.rotationYaw * 4F) / 360F + 0.5D) & 3;
            if (facing == 0) {
                ((TileQEInjector) te).rotateDirectlyTo(3);
            } else if (facing == 1) {
                ((TileQEInjector) te).rotateDirectlyTo(4);
            } else if (facing == 2) {
                ((TileQEInjector) te).rotateDirectlyTo(2);
            } else if (facing == 3) {
                ((TileQEInjector) te).rotateDirectlyTo(5);
            }
        }
        if (te instanceof TileQDematerializer
                && ((TileQDematerializer) te).canRotate()) {
            int facing = MathHelper
                    .floor_double((entity.rotationYaw * 4F) / 360F + 0.5D) & 3;
            if (facing == 0) {
                ((TileQDematerializer) te).rotateDirectlyTo(3);
            } else if (facing == 1) {
                ((TileQDematerializer) te).rotateDirectlyTo(4);
            } else if (facing == 2) {
                ((TileQDematerializer) te).rotateDirectlyTo(2);
            } else if (facing == 3) {
                ((TileQDematerializer) te).rotateDirectlyTo(5);
            }
        }
    }

}