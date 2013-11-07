package quantumcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergyComponent;
import quantumcraft.net.IQEnergyComponent;
import quantumcraft.render.RenderFiberWire;
import quantumcraft.util.BasicUtils;

public class BlockQuantumFiberWire extends BlockEnergyComponent {
    public static Icon iconTexture;

    public BlockQuantumFiberWire(int id) {
        super(id, Material.circuits);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        float[] min = {0.25F, 0.25F, 0.25F};
        float[] max = {0.75F, 0.75F, 0.75F};
        if (Block.blocksList[world.getBlockId(x - 1, y, z)] instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x - 1, y, z))
                    .canTubeConnectOnSide(world, x - 1, y, z, BasicUtils.dirXPos))
                min[0] = 0.0F;
        }
        if (Block.blocksList[world.getBlockId(x + 1, y, z)] instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x + 1, y, z))
                    .canTubeConnectOnSide(world, x + 1, y, z, BasicUtils.dirXNeg))
                max[0] = 1.0F;
        }
        if (Block.blocksList[world.getBlockId(x, y - 1, z)] instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x, y - 1, z))
                    .canTubeConnectOnSide(world, x, y - 1, z, BasicUtils.dirYPos))
                min[1] = 0.0F;
        }
        if (Block.blocksList[world.getBlockId(x, y + 1, z)] instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x, y + 1, z))
                    .canTubeConnectOnSide(world, x, y + 1, z, BasicUtils.dirYNeg))
                max[1] = 1.0F;
        }
        if (Block.blocksList[world.getBlockId(x, y, z - 1)] instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x, y, z - 1))
                    .canTubeConnectOnSide(world, x, y, z - 1, BasicUtils.dirZPos))
                min[2] = 0.0F;
        }
        if (Block.blocksList[world.getBlockId(x, y, z + 1)] instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x, y, z + 1))
                    .canTubeConnectOnSide(world, x, y, z + 1, BasicUtils.dirZNeg))
                max[2] = 1.0F;
        }
        this.setBlockBounds(min[0], min[1], min[2], max[0], max[1], max[2]);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        this.setBlockBoundsBasedOnState(w, x, y, z);
        return AxisAlignedBB.getAABBPool()
                .getAABB((double) x + this.minX, (double) y + this.minY, (double) z + this.minZ, (double) x + this.maxX,
                        (double) y + this.maxY, (double) z + this.maxZ);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return true;
    }

    @Override
    public int getRenderType() {
        return RenderFiberWire.instance().getRenderId();
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconTop = iconTopR =
                iconSide = iconBottom =
                        iconBack = iconTexture = iconRegister.registerIcon("QuantumCraft:FibreWireFullBlock");
    }
}
