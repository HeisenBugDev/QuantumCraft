package quantumcraft.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import quantumcraft.blocks.BlockQuantumFiberWire;
import quantumcraft.net.IQEnergyComponent;
import quantumcraft.util.BasicUtils;

public class RenderFiberWire extends BaseBlockRenderer {

    private static RenderFiberWire INSTANCE = new RenderFiberWire();

    public static RenderFiberWire instance(){
        return INSTANCE;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer){
        float min = 0.5F - 0.1875F;
        float max = 0.5F + 0.1875F;
        block.setBlockBounds(min, 0.0F, min, max, 1.0F, max);
        renderer.setRenderBoundsFromBlock(block);
        renderAsItem(block, renderer);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
                                    RenderBlocks renderer) {
        float coremin = 0.5F - 0.1875F;
        float coremax = 0.5F + 0.1875F;
        float bordermin = 0.0F;
        float bordermax = 1.0F;
        int joints = 0;

        if (BasicUtils.getBlockInstance(world, x - 1, y, z) instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x - 1, y, z))
                    .canFiberConnectOnSide(world, x - 1, y, z, BasicUtils.dirXPos)) {
                block.setBlockBounds(bordermin, coremin, coremin, coremin, coremax, coremax);
                renderer.setRenderBoundsFromBlock(block);
                renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 1.0F, 1.0F, 1.0F);
                joints++;
            }
        }
        if (BasicUtils.getBlockInstance(world, x + 1, y, z) instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x + 1, y, z))
                    .canFiberConnectOnSide(world, x + 1, y, z, BasicUtils.dirXNeg)) {
                block.setBlockBounds(coremax, coremin, coremin, bordermax, coremax, coremax);
                renderer.setRenderBoundsFromBlock(block);
                renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 1.0F, 1.0F, 1.0F);
                joints++;
            }
        }
        if (BasicUtils.getBlockInstance(world, x, y - 1, z) instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x, y - 1, z))
                    .canFiberConnectOnSide(world, x, y - 1, z, BasicUtils.dirYPos)) {
                block.setBlockBounds(coremin, bordermin, coremin, coremax, coremin, coremax);
                renderer.setRenderBoundsFromBlock(block);
                renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 1.0F, 1.0F, 1.0F);
                joints++;
            }
        }
        if (BasicUtils.getBlockInstance(world, x, y + 1, z) instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x, y + 1, z))
                    .canFiberConnectOnSide(world, x, y + 1, z, BasicUtils.dirYNeg)) {
                block.setBlockBounds(coremin, coremax, coremin, coremax, bordermax, coremax);
                renderer.setRenderBoundsFromBlock(block);
                renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 1.0F, 1.0F, 1.0F);
                joints++;
            }
        }
        if (BasicUtils.getBlockInstance(world, x, y, z - 1) instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x, y, z - 1))
                    .canFiberConnectOnSide(world, x, y, z - 1, BasicUtils.dirZPos)) {
                block.setBlockBounds(coremin, coremin, bordermin, coremax, coremax, coremin);
                renderer.setRenderBoundsFromBlock(block);
                renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 1.0F, 1.0F, 1.0F);
                joints++;
            }
        }
        if (BasicUtils.getBlockInstance(world, x, y, z + 1) instanceof IQEnergyComponent) {
            if (((IQEnergyComponent) BasicUtils.getBlockInstance(world, x, y, z + 1))
                    .canFiberConnectOnSide(world, x, y, z + 1, BasicUtils.dirZNeg)) {
                block.setBlockBounds(coremin, coremin, coremax, coremax, coremax, bordermax);
                renderer.setRenderBoundsFromBlock(block);
                renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 1.0F, 1.0F, 1.0F);
                joints++;
            }
        }
        if (joints > 2) {
            IIcon joint = BlockQuantumFiberWire.iconTexture;
            coremin -= 0.0625F;
            coremax += 0.0625F;
            block.setBlockBounds(coremin, coremin, coremin, coremax, coremax, coremax);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderFaceXNeg(block, x, y, z, joint);
            renderer.renderFaceXPos(block, x, y, z, joint);
            renderer.renderFaceZNeg(block, x, y, z, joint);
            renderer.renderFaceZPos(block, x, y, z, joint);
            renderer.renderFaceYNeg(block, x, y, z, joint);
            renderer.renderFaceYPos(block, x, y, z, joint);
        } else {
            block.setBlockBounds(coremin, coremin, coremin, coremax, coremax, coremax);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 1.0F, 1.0F, 1.0F);
        }
        block.setBlockBoundsBasedOnState(world, x, y, z);
        return true;
    }
}
