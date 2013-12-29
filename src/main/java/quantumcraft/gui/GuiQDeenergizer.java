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
        if (this.renderContents) {
            bindImage(GuiTextures.GUI_2SLOT_BG);
            drawQuad(30, 50, 0, 1, 0, 1, 53, 18);
            bindImage(GuiTextures.GUI_DIVIDER_V);
            drawQuad(110, 31, 0, 1, 0, 1, 2, 59);
        }
    }

    protected void drawProgressBar() {
        int x = 33;
        int y = 70;
        int width = 47 - (int) ((float) ((TileQDeenergizer) tile).QEnergyItemBuffer /
                (float) ((TileQDeenergizer) tile).lastItemValue * 47F);
        if (width == 47) {
            width = 0;
        }
        int height = 5;
        bindImage(GuiTextures.GUI_PROGRESS_BELOW);
        drawQuad(x, y, 0, (float) width / 47F, 0, (float) height / 5F, width, height);
    }

    protected void drawForeground() {
        if (this.renderContents) {
            drawBaseForeground();

            drawBasePowerBar();
            drawProgressBar();

            this.fontRenderer.drawString("Quantum De-Energizer", 15, 15, 0x000000);
            this.fontRenderer.drawString("Reserved for", 128, 55, 0x333333);
            this.fontRenderer.drawString("upgrades", 138, 65, 0x333333);

            handleHover();
        }

    }

}
