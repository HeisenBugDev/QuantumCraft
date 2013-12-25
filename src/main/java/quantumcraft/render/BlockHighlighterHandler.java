package quantumcraft.render;

import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;

public class BlockHighlighterHandler {
    @ForgeSubscribe
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event){
        if ((event.target.blockX == 0) && (event.target.blockY == 50) && (event.target.blockZ == 0)){
            drawInWorldTransmutationOverlay(event);
        }
    }

    public void drawInWorldTransmutationOverlay(DrawBlockHighlightEvent event)
    {

        double x = event.target.blockX + 0.5F;
        double y = event.target.blockY + 0.5F;
        double z = event.target.blockZ + 0.5F;
        double iPX = event.player.prevPosX + (event.player.posX - event.player.prevPosX) * event.partialTicks;
        double iPY = event.player.prevPosY + (event.player.posY - event.player.prevPosY) * event.partialTicks;
        double iPZ = event.player.prevPosZ + (event.player.posZ - event.player.prevPosZ) * event.partialTicks;

        float xScale = 1;
        float yScale = 1;
        float zScale = 1;
        float xShift = 0.1F;
        float yShift = 0.1F;
        float zShift = 0.1F;
        int chargeLevel;
        int itemChargeLevel = 0;

        chargeLevel = 1 + itemChargeLevel * 2;

        ForgeDirection sideHit = ForgeDirection.getOrientation(event.target.sideHit);

        switch (sideHit)
        {
            case UP:
            {
                xScale = chargeLevel + 0.1F;
                zScale = chargeLevel + 0.1F;
                xShift = 0;
                zShift = 0;
                break;
            }
            case DOWN:
            {
                xScale = chargeLevel + 0.1F;
                zScale = chargeLevel + 0.1F;
                xShift = 0;
                yShift = -yShift;
                zShift = 0;
                break;
            }
            case NORTH:
            {
                xScale = chargeLevel + 0.1F;
                yScale = chargeLevel + 0.1F;
                xShift = 0;
                yShift = 0;
                zShift = -zShift;
                break;
            }
            case SOUTH:
            {
                xScale = chargeLevel + 0.1F;
                yScale = chargeLevel + 0.1F;
                xShift = 0;
                yShift = 0;
                break;
            }
            case EAST:
            {
                yScale = chargeLevel + 0.1F;
                zScale = chargeLevel + 0.1F;
                yShift = 0;
                zShift = 0;
                break;
            }
            case WEST:
            {
                yScale = chargeLevel + 0.1F;
                zScale = chargeLevel + 0.1F;
                xShift = -xShift;
                yShift = 0;
                zShift = 0;
                break;
            }
            default:
                break;
        }

        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_CULL_FACE);

        for (int i = 0; i < 6; i++)
        {
            ForgeDirection forgeDir = ForgeDirection.getOrientation(i);
            int zCorrection = i == 2 ? -1 : 1;
            GL11.glPushMatrix();
            GL11.glColor3f(1,0,0);
            GL11.glTranslated(-iPX + x + xShift, -iPY + y + yShift, -iPZ + z + zShift);
            GL11.glScalef(1F * xScale, 1F * yScale, 1F * zScale);
            GL11.glRotatef(90, forgeDir.offsetX, forgeDir.offsetY, forgeDir.offsetZ);
            GL11.glTranslated(0, 0, 0.5f * zCorrection);
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glPopMatrix();
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(true);
    }
}
