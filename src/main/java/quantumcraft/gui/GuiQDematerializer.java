package quantumcraft.gui;

import net.minecraft.inventory.Container;
import quantumcraft.gui.abstractguis.GuiBase;
import quantumcraft.inventory.ContainerQDematerializer;
import quantumcraft.tile.TileQDematerializer;

public class GuiQDematerializer extends GuiBase {

    public GuiQDematerializer(Container par1Container) {
        super(par1Container, 200, 170);
        tile = ((ContainerQDematerializer) par1Container).tile;
        setStripColor(1, 0, 0);
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
        registerRedstoneButton(this);
    }

    protected void drawBackground() {
        super.drawBackground();
        if (this.renderContents) {
            bindImage(GuiTextures.GUI_1SLOT_BG);
            drawQuad(30, 50, 0, 1, 0, 1, 18, 18);
            bindImage(GuiTextures.GUI_DEMAT_ARROW);
            drawQuad(50, 55, 0, 1, 0, 1, 21, 9);
            drawDivider();
        }
    }

    protected void drawProgressBar() {
        int x = 33;
        int y = 70;
        int width = 47 - (int) ((float) ((TileQDematerializer) tile).processTime /
                (float) ((TileQDematerializer) tile).currentProcessTime * 47F);
        drawProgressBelow(width, x, y, 45);
    }

    protected void drawForeground() {
        if (this.renderContents) {
            drawBaseForeground();

            drawBasePowerBar();
            drawProgressBar();
            drawRedstoneControl();
            renderStandardText();

            handleHover();
        }
    }
}
