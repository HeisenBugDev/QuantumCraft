package quantumcraft.gui;

import quantumcraft.gui.abstractguis.GuiBase;
import quantumcraft.inventory.ContainerQEInjector;
import quantumcraft.tile.TileQEInjector;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.inventory.Container;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuiQEInjector  extends GuiBase {

    private TileQEInjector tile;

    public GuiQEInjector(Container par1Container) {
        super(par1Container, 200, 170);
        tile = ((ContainerQEInjector) par1Container).tile;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.mc.thePlayer.openContainer = this.inventorySlots;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        addHoverHandler(new HoverHandler(0), 189, 9, 9, 9);
        addHoverHandler(new HoverHandler(1), 213, 40+11, 10, 67);
        addClickHandler(new ClickHandler(0), 189, 9, 9, 9);
    }


    protected void drawBackground(){
        bindImage(GuiTextures.GUI_TPBG);
        drawQuad(0,0,0,1,0,1,200,31);
        bindImage(GuiTextures.GUI_COLS);
        GL11.glColor3f(1F,1F,0);
        drawQuad(0,0,0,1,0,1,200,31);
        bindImage(GuiTextures.GUI_BTBG);
        GL11.glColor3f(1F, 1F, 1F);
        drawQuad(0,31,0,1,0,1,200,139);
        bindImage(GuiTextures.GUI_INVBG);
        drawQuad(8, 90, 0, 1, 0, 1, 162, 76);
        bindImage(GuiTextures.GUI_ARMBG);
        drawQuad(176, 92, 0, 1, 0, 1, 18, 72);
    }

    protected void drawPowerBar() {
        float flt = (float)tile.getCurrentEnergy() / (float)tile.getMaxEnergy();
        int h = (int)(flt * 67);
        int tarx = 213+3;
        int tary = 40+11+8 + (67-h);
        bindImage(GuiTextures.GUI_PWRB);
        drawTexturedModalRect(213, 40, 8, 9, 17, 105);
        drawTexturedModalRect(tarx, tary,51, 9+(67-h), 10, h  );
        drawTexturedModalRect(tarx, 40+11+8, 33, 9, 10, 67);
    }

    protected void drawForeground(){
        bindImage(GuiTextures.GUI_BTN_CLOSE);
        GL11.glColor3f(2F, 0F, 0F);
        drawQuad(189, 9, 0, 1, 0, 1, 9, 9);
        GL11.glColor3f(1F, 1F, 1F);

        drawPowerBar();
        this.fontRenderer.drawString("Quantum Energy Injector", 15, 15, 0x000000 );

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        drawTooltips();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    protected void drawHoveringText(List par1List, int par2, int par3, FontRenderer font)
    {
        if (!par1List.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
           // RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;
            Iterator iterator = par1List.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);

                if (l > k)
                {
                    k = l;
                }
            }

            int i1 = par2 + 12;
            int j1 = par3 - 12;
            int k1 = 8;

            if (par1List.size() > 1)
            {
                k1 += 2 + (par1List.size() - 1) * 10;
            }

            if (i1 + k > this.width)
            {
                i1 -= 28 + k;
            }

            if (j1 + k1 + 6 > this.height)
            {
                j1 = this.height - k1 - 6;
            }

            this.zLevel = 500F;
            itemRenderer.zLevel = 500F;
            int l1 = -267386864;
            this.drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
            this.drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
            this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
            this.drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
            this.drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
            int i2 = 1347420415;
            int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
            this.drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
            this.drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
            this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
            this.drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

            for (int k2 = 0; k2 < par1List.size(); ++k2)
            {
                String s1 = (String)par1List.get(k2);
                font.drawStringWithShadow(s1, i1, j1, -1);

                if (k2 == 0)
                {
                    j1 += 2;
                }

                j1 += 10;
            }

            this.zLevel = 0.0F;
            itemRenderer.zLevel = 0.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
           // RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }

    protected void drawTooltips() {
        if (buffHT > -1) {
            switch (buffHT) {
                case 0:
                    renderTooltipText("Close this GUI", buffHX, buffHY);
                case 1:
                    renderTooltipText("Energy buffer: " + tile.getCurrentEnergy() + " / " + tile.getMaxEnergy() + " QEU", buffHX, buffHY);
            }
        }
    }

    protected void renderTooltipText(String text, int x, int y) {
        List l = new ArrayList();
        l.add(text);
        this.func_102021_a(l, x, y);
    }

    public int buffHX;
    public int buffHY;
    public int buffHT = -1; // 0 = close_button

    public int buffCT = -1; // 0 = close_button

    public class HoverHandler implements IHoverHandler {

        int id;

        public HoverHandler(int id) {
            this.id = id;
        }

        @Override
        public void onHover(int x, int y) {
            buffHX = x;
            buffHY = y;
            buffHT = id;
        }

        @Override
        public void onLeave() {
            buffHT = -1;
        }
    }

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
