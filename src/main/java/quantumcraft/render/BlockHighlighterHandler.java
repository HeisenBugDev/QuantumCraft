package quantumcraft.render;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import quantumcraft.tile.TileIONScanner;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.Coords;

public class BlockHighlighterHandler {

    @ForgeSubscribe
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event) {
        if (BasicUtils.getTileEntityAtTarget(event) instanceof IInWorldGui) {
            drawInWorldGui(event);
        }
//        if (IONTunnelerGlobalRemovalList.blocks == null) return;
//        for (int i = 0; i < IONTunnelerGlobalRemovalList.blocks.size(); i++) {
//            ArrayList<Coords> blocks = IONTunnelerGlobalRemovalList.getBlocks();
//            if (event.player.getEntityWorld().getBlockId(blocks.get(i).x, blocks.get(i).y, blocks.get(i).z) != 0)
//                drawInWorldBlockBreakingQueueOverlay(event, IONTunnelerGlobalRemovalList.blocks.get(i));
//        }
    }

    public void drawInWorldGui(DrawBlockHighlightEvent event) {
        Minecraft mc = FMLClientHandler.instance().getClient();

        GL11.glPushMatrix();

        double x = event.target.blockX + 0.5 - event.player.prevPosX - (event.player.posX - event.player.prevPosX);
        double y = event.target.blockY + 0.5 - event.player.prevPosY - (event.player.posY - event.player.prevPosY);
        double z = event.target.blockZ + 0.5 - event.player.prevPosZ - (event.player.posZ - event.player.prevPosZ);

        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(getAngle(z, x) + 90, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef((-1) * getAngle(Math.hypot(x, z), y) + 190, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.9F, -0.7F);
        GL11.glScalef(0.01F, 0.01F, 1F);
        GL11.glColor3f(1F, 1F, 1F);

        int length = (int) Math.round(BasicUtils.getBlockAtTarget(event).getLocalizedName().length() * 3.7);
        if (length < 100) length = 100;
        Gui.drawRect(0, 0, length, 50, 0xCC555555);

        GL11.glTranslatef(0F, 0F, -0.1F);
        GL11.glScalef(0.6F, 0.6F, 1F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        String power = EnumChatFormatting.DARK_BLUE + "Power: " +
                ((IInWorldGui) BasicUtils.getTileEntityAtTarget(event)).getPowerInfo();

        mc.fontRenderer
                .drawString(EnumChatFormatting.UNDERLINE + BasicUtils.getBlockAtTarget(event).getLocalizedName(), 13,
                        10, 0);
        mc.fontRenderer.drawString(power, 13, 22, 0);
        mc.fontRenderer
                .drawString("Status: " + ((IInWorldGui) BasicUtils.getTileEntityAtTarget(event)).getStatusText(), 13,
                        34, 0);
        if (BasicUtils.getTileEntityAtTarget(event) instanceof TileIONScanner) {
            mc.fontRenderer
                    .drawString("Speed: " + ((TileIONScanner) BasicUtils.getTileEntityAtTarget(event)).getSpeed(), 13,
                            46, 0);
        }

        GL11.glPopMatrix();
    }

    private static float getAngle(double q, double z) {
        return (float) (Math.atan2(q, z) * 360 / (2 * Math.PI));
    }

    public static void renderPlannedDemoBlock() {

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(""));
        Tessellator tessellator = Tessellator.instance;

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor3f(1, 1, 0);

        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(1, 0, 0, 1F);

        tessellator.addVertexWithUV(-0.5D, 0.5D, 0F, 0, 1);
        tessellator.addVertexWithUV(0.5D, 0.5D, 0F, 1, 1);
        tessellator.addVertexWithUV(0.5D, -0.5D, 0F, 1, 0);
        tessellator.addVertexWithUV(-0.5D, -0.5D, 0F, 0, 0);

        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

    public void drawInWorldBlockBreakingQueueOverlay(DrawBlockHighlightEvent event, Coords coord) {

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
            renderPlannedDemoBlock();
            GL11.glPopMatrix();
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(true);
    }
}
