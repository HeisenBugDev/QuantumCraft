package quantumcraft.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * A base for all the custom block renderers we're using.
 * @author LordFokas
 */
public abstract class BaseBlockRenderer implements ISimpleBlockRenderingHandler{
    protected int rid;

    protected BaseBlockRenderer()
    { rid = RenderingRegistry.getNextAvailableRenderId(); }

    @Override public int getRenderId()
    { return rid; }

    @Override public boolean shouldRender3DInInventory()
    { return true; }

    //********** ITEM RENDERING ****************************************************************//
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer){
        renderAsItem(block, renderer);
    }

    protected static void renderAsItem(Block block, RenderBlocks renderer){
        Icon[] tmap = new Icon[6];
        for(int i = 0; i < 6; i++){
            tmap[i] = block.getBlockTextureFromSide(i);
        }
        renderAsItem(block, renderer, tmap);
    }

    protected static void renderAsItem(Block block, RenderBlocks renderer, Icon[] tmap){
        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F); // Set angled view

// Y-
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0, 0, 0, tmap[0]);
        tessellator.draw();

        // Y+
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0, 0, 0, tmap[1]);
        tessellator.draw();

        // Z-
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0, 0, 0, tmap[2]);
        tessellator.draw();

        // Z+
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0, 0, 0, tmap[3]);
        tessellator.draw();

        // X-
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0, 0, 0, tmap[4]);
        tessellator.draw();

        // X+
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0, 0, 0, tmap[5]);
        tessellator.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F); // Reset angled view
    }
}