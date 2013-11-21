package quantumcraft.gui;

import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import quantumcraft.gui.abstractguis.GuiBase;
import quantumcraft.inventory.ContainerIONForge;
import quantumcraft.inventory.ContainerQDeenergizer;
import quantumcraft.tile.TileIONForge;
import quantumcraft.tile.TileQDeenergizer;
import quantumcraft.util.BasicUtils;

public class GuiIONForge extends GuiBase {

    private TileIONForge tile;

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
        int width = (int)((float) tile.progress / 16F * 47F);
        if (width == 47) {width = 0;}
        int height = 5;
        bindImage(GuiTextures.GUI_PROGRESS_BELOW);
        drawQuad(x, y, 0, (float)width/47F, 0, (float)height/5F, width, height);
    }

    protected void drawPowerBar() {
        float flt = (float) tile.getCurrentEnergy() / (float) tile.getMaxEnergy();
        int h = (int) (flt * 67);
        int tarx = 213 + 3;
        int tary = 40 + 11 + 8 + (67 - h);
        bindImage(GuiTextures.GUI_POWER_BAR);
        drawTexturedModalRect(213, 40, 8, 9, 17, 105);
        drawTexturedModalRect(tarx, tary, 51, 9 + (67 - h), 10, h);
        drawTexturedModalRect(tarx, 40 + 11 + 8, 33, 9, 10, 67);

        if (buffHT[1]) {
            drawTexturedModalRect(tarx, 40 + 11 + 8, 69, 9, 10, 67);
        }
    }

    protected void drawForeground() {
        if (this.renderContents) {
            bindImage(GuiTextures.GUI_BUTTON_CLOSE);
            GL11.glColor3f(1F, buffHT[0] ? 0F : 0.4F, buffHT[0] ? 0F : 0.4F);
            drawQuad(189, 9, 0, 1, 0, 1, 9, 9);
            GL11.glColor3f(1F, 1F, 1F);

            drawPowerBar();
            drawProgressBar();

            this.fontRenderer.drawString("I.O.N Forge", 15, 15, 0x000000);
            this.fontRenderer.drawString("Reserved for", 128, 55, 0x333333);
            this.fontRenderer.drawString("upgrades", 138, 65, 0x333333);

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);

            handleHover();

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }

    }

    protected void handleHover() {
        if (buffHT[0]) {
            renderTooltipText("Close this GUI", buffHX, buffHY);
        }
        if (buffHT[1]) {
            renderTooltipText(tile.getCurrentEnergy() + " / " + tile.getMaxEnergy() + " QEU", buffHX, buffHY);
        }
    }


    protected void handleClick(int buffCT) {
        if (buffCT > -1) {
            switch (buffCT) {
                case 0:
                    this.mc.thePlayer.closeScreen();
            }
            buffCT = -1;
        }
    }



}


/*package quantumcraft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import quantumcraft.inventory.ContainerIONForge;
import quantumcraft.tile.TileIONForge;

public class GuiIONForge extends GuiContainer {
    private TileIONForge tile;

    public GuiIONForge(Container par1Container) {
        super(par1Container);
        tile = ((ContainerIONForge) par1Container).tile;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.xSize = 176 + 19;
        this.mc.thePlayer.openContainer = this.inventorySlots;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = this.tile.getInvName();
        this.fontRenderer.drawString(s, (this.xSize - 19) / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer
                .drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        this.mc.renderEngine.bindTexture(new ResourceLocation("quantumcraft", "textures/gui/ionforge.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int h2 = (int) (76 - ((float) tile.getCurrentEnergy() / (float) tile.getMaxEnergy() * 76));

        this.drawTexturedModalRect(k + 177, l + 37 + h2, 195, 16 + h2, 12, 76 - h2);
        this.drawTexturedModalRect(k + 177, l + 37, 207, 16, 12, 76);
        if (tile.progress != 0) {
            int h = (int) (16 - ((float) tile.progress / (float) 10 * 16));
            this.drawTexturedModalRect(k + 69, l + 34 + h, 195, h, 16, 16 - h);
        }
    }
}                                                                                    */
