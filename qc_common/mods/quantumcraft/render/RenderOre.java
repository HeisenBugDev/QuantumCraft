package mods.quantumcraft.render;

import mods.quantumcraft.blocks.BlockOreQuantonium;
import mods.quantumcraft.core.Config;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

/**
 * Renders Naquadah and Naquadria ore blocks.
 *
 * @author LordFokas
 */
public class RenderOre extends BaseBlockRenderer {
    private static RenderOre INSTANCE = new RenderOre();

    public static RenderOre instance(){
        return INSTANCE;
    }

    Icon base;
    Icon ore;

    public void registerIcons(IconRegister ir) {
        base = ir.registerIcon(Config.getTextureName(Config.NameTextureQBase));
        ore = ir.registerIcon(Config.getTextureName(Config.NameTextureQOre));
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks r){

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
        r.overrideBlockTexture = null;
        return false;
    }
}