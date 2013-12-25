package quantumcraft.render;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import quantumcraft.core.QuantumCraft;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.Coords;
import quantumcraft.util.IONTunnelerGlobalRemovalList;

import java.util.ArrayList;

public class BlockHighlighterHandler {

    @ForgeSubscribe
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event) {
        if (IONTunnelerGlobalRemovalList.blocks == null) return;
        for (int i = 0; i < IONTunnelerGlobalRemovalList.blocks.size(); i++) {
            ArrayList<Coords> blocks = IONTunnelerGlobalRemovalList.getBlocks();
            if(event.player.getEntityWorld().getBlockId(blocks.get(i).x, blocks.get(i).y, blocks.get(i).z) != 0)
            drawInWorldTransmutationOverlay(event, IONTunnelerGlobalRemovalList.blocks.get(i));
        }
    }

    public static void renderPulsingQuad(ResourceLocation texture, float maxTransparency) {

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(""));
        Tessellator tessellator = Tessellator.instance;

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor3f(1, 1, 0);

        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(1, 0, 0, 0.5F);

        tessellator.addVertexWithUV(-0.5D, 0.5D, 0F, 0, 1);
        tessellator.addVertexWithUV(0.5D, 0.5D, 0F, 1, 1);
        tessellator.addVertexWithUV(0.5D, -0.5D, 0F, 1, 0);
        tessellator.addVertexWithUV(-0.5D, -0.5D, 0F, 0, 0);

        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

    public void drawInWorldTransmutationOverlay(DrawBlockHighlightEvent event, Coords coord) {

        double x = coord.x;
        double y = coord.y;
        double z = coord.z;
        double iPX = event.player.prevPosX + (event.player.posX - event.player.prevPosX) * event.partialTicks;
        double iPY = event.player.prevPosY + (event.player.posY - event.player.prevPosY) * event.partialTicks;
        double iPZ = event.player.prevPosZ + (event.player.posZ - event.player.prevPosZ) * event.partialTicks;

        float xScale = 1.01F;
        float yScale = 1.01F;
        float zScale = 1.01F;
        float xShift = 0.5F;
        float yShift = 0.5F;
        float zShift = 0.5F;
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_CULL_FACE);

        for (int i = 0; i < 6; i++) {
            ForgeDirection forgeDir = ForgeDirection.getOrientation(i);
            int zCorrection = i == 2 ? -1 : 1;
            GL11.glPushMatrix();
            GL11.glColor3f(1, 0, 0);
            GL11.glTranslated(-iPX + x + xShift, -iPY + y + yShift, -iPZ + z + zShift);
            GL11.glScalef(1F * xScale, 1F * yScale, 1F * zScale);
            GL11.glRotatef(90, forgeDir.offsetX, forgeDir.offsetY, forgeDir.offsetZ);
            GL11.glTranslated(0, 0, 0.5f * zCorrection);
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
            renderPulsingQuad(QuantumCraft.overlayTex, 0.75F);
            GL11.glPopMatrix();
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(true);
    }
}
