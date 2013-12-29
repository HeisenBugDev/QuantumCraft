package quantumcraft.gui;

import net.minecraft.inventory.Container;
import quantumcraft.gui.abstractguis.GuiBase;
import quantumcraft.inventory.ContainerQDeenergizer;
import quantumcraft.tile.TileQDeenergizer;

public class GuiQDeenergizer extends GuiBase {

    public GuiQDeenergizer(Container par1Container) {
        super(par1Container, 200, 170);
        tile = ((ContainerQDeenergizer) par1Container).tile;
        setStripColor(0.7F, 0.7F, 0.7F);
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
        drawTwoSlot();
    }

    protected void drawProgressBar() {
        int x = 33;
        int y = 70;
        int width = 47 - (int) ((float) ((TileQDeenergizer) tile).QEnergyItemBuffer /
                (float) ((TileQDeenergizer) tile).lastItemValue * 47F);
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
