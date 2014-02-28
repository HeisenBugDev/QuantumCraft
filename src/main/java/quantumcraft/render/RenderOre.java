package quantumcraft.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import quantumcraft.core.Loader;

/**
 * Renders Naquadah and Naquadria ore blocks.
 * ^ not anymore :D -sammko
 *
 * @author LordFokas
 */
public class RenderOre extends BaseBlockRenderer {
    private static RenderOre INSTANCE = new RenderOre();

    public static RenderOre instance() {
        return INSTANCE;
    }

    IIcon base;
    IIcon ore;

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks r) {
        base = Loader.IconLoader.quantonium_ore_base;
        ore = Loader.IconLoader.quantonium_ore;
        r.overrideBlockTexture = base;
        r.renderStandardBlock(block, x, y, z);
        r.overrideBlockTexture = null;
        Tessellator t = Tessellator.instance;
        t.setBrightness(220);
        t.setColorOpaque_I(0xFFFFFF);
        r.renderFaceXPos(block, x, y, z, ore);
        r.renderFaceXNeg(block, x, y, z, ore);
        r.renderFaceYPos(block, x, y, z, ore);
        r.renderFaceYNeg(block, x, y, z, ore);
        r.renderFaceZPos(block, x, y, z, ore);
        r.renderFaceZNeg(block, x, y, z, ore);
        return false;
    }
}