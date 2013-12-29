package quantumcraft.gui;

import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import quantumcraft.gui.abstractguis.GuiBase;
import quantumcraft.inventory.ContainerIONForge;
import quantumcraft.tile.TileIONForge;

public class GuiIONForge extends GuiBase {

    public GuiIONForge(Container par1Container) {
        super(par1Container, 200, 170);
        tile = ((ContainerIONForge) par1Container).tile;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.mc.thePlayer.openContainer = this.inventorySlots;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        addHoverHandler(new HoverHandler(0, this), 189, 9, 9, 9);
        addHoverHandler(new HoverHandler(1, this), 206, 50, 12, 68);
        addClickHandler(new ClickHandler(0), 189, 9, 9, 9);
    }

    protected void drawBackground() {
        if (this.renderContents) {
            bindImage(GuiTextures.GUI_TOP_BG);
            drawQuad(0, 0, 0, 1, 0, 1, 200, 31);
            bindImage(GuiTextures.GUI_COLOR_STRIP);
            GL11.glColor3f(1F, .5F, 0F);
            drawQuad(0, 0, 0, 1, 0, 1, 200, 31);
            bindImage(GuiTextures.GUI_BOTTOM_BG);
            GL11.glColor3f(1F, 1F, 1F);
            drawQuad(0, 31, 0, 1, 0, 1, 200, 139);
            bindImage(GuiTextures.GUI_INVENTORY_BG);
            drawQuad(8, 90, 0, 1, 0, 1, 162, 76);
            bindImage(GuiTextures.GUI_ARMOR_BG);
            drawQuad(176, 92, 0, 1, 0, 1, 18, 72);
            bindImage(GuiTextures.GUI_4SLOT_BG);
            drawQuad(30, 38, 0, 1, 0, 1, 53, 46);
            bindImage(GuiTextures.GUI_DIVIDER_V);
            drawQuad(110, 31, 0, 1, 0, 1, 2, 59);
        }
    }

    protected void drawProgressBar() {
        int x = 33;
        int y = 59;
        int width = (int)((float) ((TileIONForge) tile).progress / 16F * 47F);
        if (width == 47) {width = 0;}
        int height = 5;
        bindImage(GuiTextures.GUI_PROGRESS_BELOW);
        drawQuad(x, y, 0, (float)width/47F, 0, (float)height/5F, width, height);
    }

    protected void drawForeground() {
        if (this.renderContents) {
            bindImage(GuiTextures.GUI_BUTTON_CLOSE);
            GL11.glColor3f(1F, buffHT[0] ? 0F : 0.4F, buffHT[0] ? 0F : 0.4F);
            drawQuad(189, 9, 0, 1, 0, 1, 9, 9);
            GL11.glColor3f(1F, 1F, 1F);

            drawBasePowerBar();
            drawProgressBar();

            this.fontRenderer.drawString("I.O.N Forge", 15, 15, 0x000000);
            this.fontRenderer.drawString("Reserved for", 128, 55, 0x333333);
            this.fontRenderer.drawString("upgrades", 138, 65, 0x333333);

            handleHover();

        }

    }

}