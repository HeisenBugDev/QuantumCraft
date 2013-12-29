package quantumcraft.gui;

import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import quantumcraft.gui.abstractguis.GuiBase;
import quantumcraft.inventory.ContainerQCapacitor;
import quantumcraft.tile.TileQCapacitor;

public class GuiQCapacitor extends GuiBase {

    public GuiQCapacitor(Container container) {
        super(container, 200, 170);
        tile = ((ContainerQCapacitor) container).tile;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.mc.thePlayer.openContainer = this.inventorySlots;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        addHoverHandler(new HoverHandler(0, this), 189, 9, 9, 9);
        addHoverHandler(new HoverHandler(1, this), 15, 36, 81, 16);
        addClickHandler(new ClickHandler(0), 189, 9, 9, 9);
    }

    protected void drawBackground() {
        super.drawBackground();
        if (this.renderContents) {
            bindImage(GuiTextures.GUI_DIVIDER_V);
            drawQuad(110, 31, 0, 1, 0, 1, 2, 59);
            bindImage(GuiTextures.GUI_CAPACITOR_2SLOT);
            drawQuad(30, 60, 0, 1, 0, 1, 53, 25);
        }
    }


    protected void drawPowerBar() {
        float flt = (float) tile.getCurrentEnergy() / (float) tile.getMaxEnergy();
        int w = (int) (flt * 79);
        int tarx = 23;
        int tary = 44;
        bindImage(GuiTextures.GUI_POWER_BAR);
        drawTexturedModalRect(tarx, tary, 85, 35, 81, 16);
        drawTexturedModalRect(tarx+1, tary+1, 86, 69, w, 14);
        drawTexturedModalRect(tarx, tary, 85, 13, 81, 16);
    }

    protected void drawForeground() {
        if (this.renderContents) {
            bindImage(GuiTextures.GUI_BUTTON_CLOSE);
            GL11.glColor3f(1F, buffHT[0] ? 0F : 0.4F, buffHT[0] ? 0F : 0.4F);
            drawQuad(189, 9, 0, 1, 0, 1, 9, 9);
            GL11.glColor3f(1F, 1F, 1F);
            drawPowerBar();

            this.fontRenderer.drawString(((TileQCapacitor) tile).getInvName(), 15, 15, 0x000000);
            this.fontRenderer.drawString(tile.getCurrentEnergy() + " / " + tile.getMaxEnergy(), 23, 63, 0x333333);

            this.fontRenderer.drawString("Reserved for", 128, 55, 0x333333);
            this.fontRenderer.drawString("upgrades", 138, 65, 0x333333);

            handleHover();
        }

    }

}
