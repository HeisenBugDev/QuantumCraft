package quantumcraft.gui;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import quantumcraft.gui.abstractguis.GuiBase;
import quantumcraft.gui.handlers.HoverHandler;
import quantumcraft.inventory.ContainerQEInjector;
import quantumcraft.tile.TileQEInjector;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuiQEInjector extends GuiBase {

    private TileQEInjector tile;
    private boolean closeButtonHover = false;

    public GuiQEInjector(Container par1Container) {
        super(par1Container, 200, 170);
        tile = ((ContainerQEInjector) par1Container).tile;
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
        if (this.renderI) {
            bindImage(GuiTextures.GUI_TPBG);
            drawQuad(0, 0, 0, 1, 0, 1, 200, 31);
            bindImage(GuiTextures.GUI_COLS);
            GL11.glColor3f(1F, 1F, 0);
            drawQuad(0, 0, 0, 1, 0, 1, 200, 31);
            bindImage(GuiTextures.GUI_BTBG);
            GL11.glColor3f(1F, 1F, 1F);
            drawQuad(0, 31, 0, 1, 0, 1, 200, 139);
            bindImage(GuiTextures.GUI_INVBG);
            drawQuad(8, 90, 0, 1, 0, 1, 162, 76);
            bindImage(GuiTextures.GUI_ARMBG);
            drawQuad(176, 92, 0, 1, 0, 1, 18, 72);
            bindImage(GuiTextures.GUI_INEJCTOR_BG);
            drawQuad(30, 50, 0, 1, 0, 1, 53, 18);
            bindImage(GuiTextures.GUI_DIVIDER_V);
            drawQuad(110, 31, 0, 1, 0, 1, 2, 59);

        }
    }

    protected void drawPowerBar() {
        float flt = (float) tile.getCurrentEnergy() / (float) tile.getMaxEnergy();
        int h = (int) (flt * 67);
        int tarx = 213 + 3;
        int tary = 40 + 11 + 8 + (67 - h);
        bindImage(GuiTextures.GUI_PWRB);
        drawTexturedModalRect(213, 40, 8, 9, 17, 105);
        drawTexturedModalRect(tarx, tary, 51, 9 + (67 - h), 10, h);
        drawTexturedModalRect(tarx, 40 + 11 + 8, 33, 9, 10, 67);

        if (buffHT[1]) {
            drawTexturedModalRect(tarx, 40 + 11 + 8, 69, 9, 10, 67);
        }
    }

    protected void drawForeground() {
        if (this.renderI) {
            bindImage(GuiTextures.GUI_BTN_CLOSE);
            GL11.glColor3f(1F, buffHT[0] ? 0F : 0.4F, buffHT[0] ? 0F : 0.4F);
            drawQuad(189, 9, 0, 1, 0, 1, 9, 9);
            GL11.glColor3f(1F, 1F, 1F);

            drawPowerBar();

            this.fontRenderer.drawString("Quantum Energy Injector", 15, 15, 0x000000);

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);

            drawTooltips();

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }

    }

    protected void drawTooltips() {
        if (buffHT[0]) {
            renderTooltipText("Close this GUI (duh)", buffHX, buffHY);
        }
        if (buffHT[1]) {
            renderTooltipText(tile.getCurrentEnergy() + " / " + tile.getMaxEnergy() + " QEU", buffHX, buffHY);
        }
    }

    protected void renderTooltipText(String text, int x, int y) {
        List l = new ArrayList();
        l.add(text);
        this.drawHoveringText(l, x, y, this.fontRenderer);

    }

    public int buffHX;
    public int buffHY;
    public boolean[] buffHT = new boolean[2];

    public int buffCT = 0;


    protected void handleClick() {
        if (buffCT > -1) {
            switch (buffCT) {
                case 0:
                    this.mc.thePlayer.closeScreen();
            }
            buffCT = -1;
        }
    }

    public class ClickHandler implements IClickHandler {

        final int id;

        public ClickHandler(int id) {
            this.id = id;
        }

        @Override
        public void onClick(int x, int y) {
            buffCT = id;
            handleClick();
        }
    }


      /*
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = this.tile.getInvName();
        this.fontRenderer.drawString(s, (this.xSize-19) / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        //this.fontRenderer.drawString("MAX: " + tile.maxival + "; CUR: " + tile.currentival,
        //        this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 15, 4210752);
        this.fontRenderer
                .drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //this.mc.renderEngine.func_110577_a("/mods/QuantumCraft/textures/gui/deenergizer.png");
        this.mc.renderEngine.func_110577_a(new ResourceLocation("quantumcraft", "textures/gui/einjector.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int h2 = (int) (76 - ((float) tile.getCurrentEnergy() / (float) tile.getMaxEnergy() * 76));

        this.drawTexturedModalRect(k + 177, l + 37 + h2, 195, 16 + h2, 12, 76 - h2);
        this.drawTexturedModalRect(k + 177, l + 37, 207, 16, 12, 76);

        if (tile.upgradeID != 0) {
            itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Loader.ItemUpgrade, 1, tile.upgradeID), k+174, l+129);
        }

        if (tile.maxival != 0) {
            int h = (int) (16 - ((float) tile.currentival / (float) tile.maxival * 16));
            this.drawTexturedModalRect(k + 69, l + 34 + h, 195, 0 + h, 16, 16 - h);
        }


    }   */

}
