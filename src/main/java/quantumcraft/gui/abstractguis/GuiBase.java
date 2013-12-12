package quantumcraft.gui.abstractguis;

/**
 * THIS CLASS HAS BEEN PROVIDED BY OUR LOVELY FRIEND LordFokas and slightly modified by myself.
 * BE SURE TO CHECK OUT HIS MOD STARGATETECH2 ON GITHUB.
 */

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import quantumcraft.gui.GuiSteamGenerator;
import quantumcraft.gui.GuiTextures;
import quantumcraft.tile.TileSteamGenerator;
import quantumcraft.tile.abstracttiles.TileMachineBase;

import java.util.ArrayList;
import java.util.List;

public abstract class GuiBase extends GuiContainer {
    private boolean onBackground;
    private boolean isNativeRender;
    protected ResourceLocation bgImage = null;
    protected TileMachineBase tile;
    public int buffHX;
    public int buffHY;
    public boolean[] buffHT = new boolean[64];

    private ArrayList<HandlerWrapper<IClickHandler>> clickHandlers = new ArrayList<HandlerWrapper<IClickHandler>>();
    private ArrayList<HandlerWrapper<IHoverHandler>> hoverHandlers = new ArrayList<HandlerWrapper<IHoverHandler>>();

    public static interface IClickHandler {
        public void onClick(int x, int y);
    }

    protected void handleHover() {
        if (buffHT[0]) {
            renderTooltipText("Close this GUI", buffHX, buffHY);
        }
        if (buffHT[1]) {
            if (this instanceof GuiSteamGenerator) {
                renderTooltipText(((TileSteamGenerator) tile).getSteamBuffer() + " / " +
                        ((TileSteamGenerator) tile).getSteamTankMax() + " Steam", buffHX, buffHY);
            } else {
                renderTooltipText(tile.getCurrentEnergy() + " / " + tile.getMaxEnergy() + " QEU", buffHX, buffHY);
            }
        }
    }

    protected void drawBaseBG() {
        bindImage(GuiTextures.GUI_TOP_BG);
        drawQuad(0, 0, 0, 1, 0, 1, 200, 31);
        bindImage(GuiTextures.GUI_COLOR_STRIP);
        GL11.glColor3f(1F, 0F, 0F);
        drawQuad(0, 0, 0, 1, 0, 1, 200, 31);
        bindImage(GuiTextures.GUI_BOTTOM_BG);
        GL11.glColor3f(1F, 1F, 1F);
        drawQuad(0, 31, 0, 1, 0, 1, 200, 139);
        bindImage(GuiTextures.GUI_INVENTORY_BG);
        drawQuad(8, 90, 0, 1, 0, 1, 162, 76);
        bindImage(GuiTextures.GUI_ARMOR_BG);
        drawQuad(176, 92, 0, 1, 0, 1, 18, 72);
    }

    protected void drawBasePowerBar() {
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

    public class ClickHandler implements IClickHandler {

        final int id;

        public ClickHandler(int id) {
            this.id = id;
        }

        @Override
        public void onClick(int x, int y) {
            buffCT = id;
            handleClick(id);
        }
    }

    protected void handleClick(int buffCT) {
        if (buffCT > -1) {
            switch (buffCT) {
                case 0:
                    this.mc.thePlayer.closeScreen();
            }
        }
    }

    int buffCT = -1;

    public static interface IHoverHandler {
        public void onHover(int x, int y);

        public void onLeave();
    }

    public class HoverHandler implements GuiBase.IHoverHandler {

        int id;
        GuiBase gui;

        public HoverHandler(int id, GuiBase i) {
            this.id = id;
            this.gui = i;
        }

        @Override
        public void onHover(int x, int y) {
            gui.buffHX = x;
            gui.buffHY = y;
            gui.buffHT[this.id] = true;
        }

        @Override
        public void onLeave() {
            gui.buffHT[this.id] = false;
        }
    }

    protected void renderTooltipText(String text, int x, int y) {
        List<String> l = new ArrayList<String>();
        l.add(text);
        this.drawHoveringText(l, x, y, this.fontRenderer);

    }


    private static class HandlerWrapper<H> {
        public final H handler;
        public final int minX, minY, maxX, maxY;

        public HandlerWrapper(H h, int x0, int y0, int x1, int y1) {
            handler = h;
            minX = x0;
            minY = y0;
            maxX = x1;
            maxY = y1;
        }
    }

    public class TextHandler {
        private char[] chars = new char[64];
        private int pos = 0;

        public void onKey(char k, int c) {
            if (c == 14) {
                chars[pos] = ' ';
                if (pos > 0) pos--;
            } else if (c >= 2 && c <= 11 || c >= 16 && c <= 25 || c >= 30 && c <= 38 || c >= 44 && c <= 50) {
                if (pos < 64) {
                    chars[pos] = k;
                    pos++;
                }
            }
        }

        public String getString(int count) {
            int start = count < pos ? pos - count : 0;
            StringBuffer buff = new StringBuffer();
            for (int i = start; i < pos; i++) {
                buff.append(chars[i]);
            }
            return buff.toString();
        }
    }

    public boolean renderContents = true;

    protected GuiBase(Container container, int x, int y) {
        super(container);
        /*xSize = 20;
        ySize = 20; */
        xSize = x + 18;
        ySize = y + 18;
    }

    protected void addClickHandler(IClickHandler handler, int x, int y, int xS, int yS) {
        clickHandlers.add(new HandlerWrapper<IClickHandler>(handler, x, y, x + xS, y + yS));
    }

    protected void addHoverHandler(IHoverHandler handler, int x, int y, int xS, int yS) {
        hoverHandlers.add(new HandlerWrapper<IHoverHandler>(handler, x, y, x + xS, y + yS));
    }

    @Override
    protected final void mouseClicked(int x, int y, int btn) {
        super.mouseClicked(x, y, btn);
        if (btn == 0) {
            x -= (guiLeft + 9);
            y -= (guiTop + 9);
            for (HandlerWrapper<IClickHandler> hw : clickHandlers) {
                if (hw.minX <= x && hw.maxX > x && hw.minY <= y && hw.maxY > y) {
                    hw.handler.onClick(x, y);
                }
            }
        }
    }

    @Override
    public final void handleMouseInput() {
        super.handleMouseInput();
        int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
        if (Mouse.getEventButton() == -1) {
            x -= (guiLeft + 9);
            y -= (guiTop + 9);
            for (HandlerWrapper<IHoverHandler> hw : hoverHandlers) {
                if (hw.minX <= x && hw.maxX > x && hw.minY <= y && hw.maxY > y) {
                    hw.handler.onHover(x, y);
                } else {
                    hw.handler.onLeave();
                }
            }
        }
    }

    @Override
    protected final void keyTyped(char key, int code) {
        if (code == 1 || code == 18) {
            this.mc.thePlayer.closeScreen();
        } else {
            onKeyTyped(key, code);
        }
    }

    int rcycle = 8;

    protected void onKeyTyped(char key, int code) {
    }

    @Override
    protected final void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        onBackground = true;
        /*
        if (xSize <= (targetX - rcycle)) {
            xSize += rcycle;
        } else if (xSize > (targetX - rcycle)) {
            xSize = targetX;
        }
        if (ySize <= (targetY - rcycle)) {
            ySize += rcycle;
        } else if (ySize > (targetY - rcycle)) {
            ySize = targetY;
        }
        if (ySize == targetY && xSize == targetX) {

            renderContents = true;
        }
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2; */


        GL11.glEnable(GL11.GL_BLEND);

        bindImage(GuiTextures.GUI_WHITELINE);
        isNativeRender = true;
        //draw layer N
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glStencilFunc(GL11.GL_ALWAYS, 1, -1);
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
        draw9cut();
        isNativeRender = false;
        bindImage(GuiTextures.GUI_RGYB);


        //GL11.glTranslatef(+xSize/2, +ySize/2, 0);
        //GL11.glRotatef(90, 0.0F, 0.0F, 1.0F);
        GL11.glStencilFunc(GL11.GL_EQUAL, 1, -1);
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_KEEP);
        drawQuad(0 - 9, 0 - 9, 0, 1, 0, 1, xSize, ySize);
        GL11.glDisable(GL11.GL_STENCIL_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);

        drawBackground();

        //RENDER TOP LAYER
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        bindImage(GuiTextures.GUI_BASE);
        isNativeRender = true;
        draw9cut();
        isNativeRender = false;
        onBackground = false;
    }

    protected final void draw9cut() {
        drawLocalQuad(0, 0, 0, 24, 0, 24, 24, 24);
        drawLocalQuad(xSize - 24, 0, 40, 64, 0, 24, 24, 24);
        drawLocalQuad(0, ySize - 24, 0, 24, 40, 64, 24, 24);
        drawLocalQuad(xSize - 24, ySize - 24, 40, 64, 40, 64, 24, 24);
        drawLocalQuad(24, 0, 24, 40, 0, 9, xSize - 48, 9);
        drawLocalQuad(24, ySize - 9, 24, 40, 55, 64, xSize - 48, 9);
        drawLocalQuad(0, 24, 0, 9, 24, 40, 9, ySize - 48);
        drawLocalQuad(xSize - 9, 24, 55, 64, 24, 40, 9, ySize - 48);
    }

    protected void drawBackground() {
    }

    @Override
    protected final void drawGuiContainerForegroundLayer(int par1, int par2) {
        // there's more stuff to add here, but it'll stay like this for now.
        drawForeground();
    }

    protected void drawForeground() {
    }

    protected void drawLocalQuad(float x, float y, float xMin, float xMax, float yMin, float yMax, float xStep,
                                 float yStep) {
        drawQuad(x, y, xMin / 256F, xMax / 256F, yMin / 256F, yMax / 256F, xStep, yStep);
    }

    protected void drawQuad(float x, float y, float xMin, float xMax, float yMin, float yMax, float xStep,
                            float yStep) {
        if (!isNativeRender) {
            x += 9;
            y += 9;
        }
        if (onBackground) {
            x += guiLeft;
            y += guiTop;
        }
        //GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);

        GL11.glTexCoord3f(xMin, yMin, zLevel); // xy
        GL11.glVertex2f(x, y);

        GL11.glTexCoord3f(xMin, yMax, zLevel); // xY
        GL11.glVertex2f(x, y + yStep);

        GL11.glTexCoord3f(xMax, yMin, zLevel); // Xy
        GL11.glVertex2f(x + xStep, y);

        GL11.glTexCoord3f(xMax, yMax, zLevel); // XY
        GL11.glVertex2f(x + xStep, y + yStep);

        GL11.glEnd();
        //GL11.glEnable(GL11.GL_LIGHTING);
    }

    protected void bindImage(ResourceLocation rl) {
        mc.renderEngine.bindTexture(rl);
    }

    protected void drawLeft(String s, int x, int y, int color) {
        x += 9;
        y += 9;
        fontRenderer.drawString(s, x, y, color);
    }

    protected void drawCentered(String s, int xMid, int y, int color) {
        drawLeft(s, xMid - fontRenderer.getStringWidth(s) / 2, y, color);
    }
}