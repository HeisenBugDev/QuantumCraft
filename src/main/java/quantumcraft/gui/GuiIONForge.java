package quantumcraft.gui;

import net.minecraft.inventory.Container;
import quantumcraft.gui.abstractguis.GuiBase;
import quantumcraft.inventory.ContainerIONForge;
import quantumcraft.tile.TileIONForge;

public class GuiIONForge extends GuiBase {

    public GuiIONForge(Container par1Container) {
        super(par1Container, 200, 170);
        tile = ((ContainerIONForge) par1Container).tile;
        setStripColor(1, 0.5F, 0);
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
        super.drawBackground();
        if (this.renderContents) {
            bindImage(GuiTextures.GUI_4SLOT_BG);
            drawQuad(30, 38, 0, 1, 0, 1, 53, 46);
            drawDivider();
        }
    }

    protected void drawProgressBar() {
        int x = 33;
        int y = 59;
        int width = (int) ((float) ((TileIONForge) tile).progress / 16F * 47F);
        drawProgressBelow(width, x, y, 47);
    }

    protected void drawForeground() {
        if (this.renderContents) {
            drawBaseForeground();

            drawBasePowerBar();
            drawProgressBar();

            renderStandardText();

            handleHover();

        }

    }

}