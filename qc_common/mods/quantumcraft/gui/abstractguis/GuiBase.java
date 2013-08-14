package mods.quantumcraft.gui.abstractguis;

/**
 * THIS CLASS HAS BEEN PROVIDED BY OUR LOVELY FRIEND LordFokas and slightly modified by myself.
 * BE SURE TO CHECK OUT HIS MOD STARGATETECH2 ON GITHUB.
 */

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import mods.quantumcraft.gui.GuiTextures;
import net.java.games.input.Keyboard;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.ARBTextureSwizzle;

public abstract class GuiBase extends GuiContainer {
	private boolean onBackground;
	private boolean isNativeRender;
	protected ResourceLocation bgImage = null;
	
	private ArrayList<HandlerWrapper<IClickHandler>> clickHandlers = new ArrayList<HandlerWrapper<IClickHandler>>();
	private ArrayList<HandlerWrapper<IHoverHandler>> hoverHandlers = new ArrayList<HandlerWrapper<IHoverHandler>>();
	
	public static interface IClickHandler{
		public void onClick(int x, int y);
	}
	
	public static interface IHoverHandler{
		public void onHover(int x, int y);
		public void onLeave();
	}
	
	private static class HandlerWrapper<H>{
		public final H handler;
		public final int minX, minY, maxX, maxY;
		
		public HandlerWrapper(H h, int x0, int y0, int x1, int y1){
			handler = h;
			minX = x0;
			minY = y0;
			maxX = x1;
			maxY = y1;
		}
	}
	
	public class TextHandler{
		private char[] chars = new char[64];
		private int pos = 0;

		public void onKey(char k, int c){
			if(c == 14){
				chars[pos] = ' ';
				if(pos > 0) pos--;
			}else if(c >= 2 && c <= 11 || c >= 16 && c <= 25 || c >= 30 && c <= 38 || c >= 44 && c <= 50){
				if(pos < 64){
					chars[pos] = k;
					pos++;
				}
			}
		}
		
		public String getString(int count){
			int start = count < pos ? pos-count : 0;
			StringBuffer buff = new StringBuffer();
			for(int i = start; i < pos; i++){
				buff.append(chars[i]);
			}
			return buff.toString();
		}
	}
	
	protected GuiBase(Container container, int x, int y) {
		super(container);
		xSize = x + 18;
		ySize = y + 18;
	}
	
	protected void addClickHandler(IClickHandler handler, int x, int y, int xS, int yS){
		clickHandlers.add(new HandlerWrapper<IClickHandler>(handler, x, y, x+xS, y+yS));
	}
	
	protected void addHoverHandler(IHoverHandler handler, int x, int y, int xS, int yS){
		hoverHandlers.add(new HandlerWrapper<IHoverHandler>(handler, x, y, x+xS, y+yS));
	}
	
	@Override
	protected final void mouseClicked(int x, int y, int btn){
		super.mouseClicked(x, y, btn);
		if(btn == 0){
			x -= (guiLeft + 9);
			y -= (guiTop + 9);
			for(HandlerWrapper<IClickHandler> hw : clickHandlers){
				if(hw.minX <= x && hw.maxX > x && hw.minY <= y && hw.maxY > y){
					hw.handler.onClick(x, y);
				}
			}
		}
	}
	
	@Override
	public final void handleMouseInput(){
		super.handleMouseInput();
		int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
		int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
		if(Mouse.getEventButton() == -1){
			x -= (guiLeft + 9);
			y -= (guiTop + 9);
			for(HandlerWrapper<IHoverHandler> hw : hoverHandlers){
				if(hw.minX <= x && hw.maxX > x && hw.minY <= y && hw.maxY > y){
					hw.handler.onHover(x, y);
				}else{
					hw.handler.onLeave();
				}
			}
		}
	}
	
	@Override
	protected final void keyTyped(char key, int code){
		if(code == 1){
			this.mc.thePlayer.closeScreen();
		}else{
			onKeyTyped(key, code);
		}
	}
	
	protected void onKeyTyped(char key, int code){}
	
	@Override
	protected final void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		onBackground = true;
        GL11.glPopMatrix();
        //SWIZZLE WHITE LINE TO ALPHA
        bindImage(GuiTextures.GUI_WHTL);
        int swizzleMask[] = {GL11.GL_ZERO, GL11.GL_ZERO, GL11.GL_ZERO, GL11.GL_RED};
        ByteBuffer bytes = ByteBuffer.allocateDirect(64) ;
        IntBuffer ints = bytes.asIntBuffer();
        ints.put(swizzleMask);
        GL11.glTexParameter(GL11.GL_TEXTURE_2D, ARBTextureSwizzle.GL_TEXTURE_SWIZZLE_RGBA, ints);
        //RENDER WHITE LINE IN ALPHA
        draw9cut();
        bindImage(GuiTextures.GUI_RGYB);
        GL11.glBlendFunc(GL11.GL_ADD, GL11.GL_ADD);
        drawQuad(0,0,0,1,0,1,1,1);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if(bgImage != null){
			bindImage(bgImage);
			int x = xSize - 18;
			int y = ySize - 18;
			drawLocalQuad(0, 0, 0, x, 0, y, x, y);
		}
		bindImage(GuiTextures.GUI_BASE);
		isNativeRender = true;
        draw9cut();
		isNativeRender = false;
		drawBackground();
		onBackground = false;
        GL11.glPushMatrix();
	}

    protected final void draw9cut() {
        drawLocalQuad(0, 0, 0, 24, 0, 24, 24, 24);
        drawLocalQuad(xSize-24, 0, 40, 64, 0, 24, 24, 24);
        drawLocalQuad(0, ySize-24, 0, 24, 40, 64, 24, 24);
        drawLocalQuad(xSize-24, ySize-24, 40, 64, 40, 64, 24, 24);
        drawLocalQuad(24, 0, 24, 40, 0, 9, xSize-48, 9);
        drawLocalQuad(24, ySize-9, 24, 40, 55, 64, xSize-48, 9);
        drawLocalQuad(0, 24, 0, 9, 24, 40, 9, ySize-48);
        drawLocalQuad(xSize-9, 24, 55, 64, 24, 40, 9, ySize-48);
    }

	protected void drawBackground(){}
	
	@Override
	protected final void drawGuiContainerForegroundLayer(int par1, int par2){
		// there's more stuff to add here, but it'll stay like this for now.
		drawForeground();
	}
	
	protected void drawForeground(){}
	
	protected void drawLocalQuad(float x, float y, float xMin, float xMax, float yMin, float yMax, float xStep, float yStep){
		drawQuad(x, y, xMin / 256F, xMax / 256F, yMin / 256F, yMax / 256F, xStep, yStep);
	}
	
	protected void drawQuad(float x, float y, float xMin, float xMax, float yMin, float yMax, float xStep, float yStep){
		if(!isNativeRender){
			x += 9;
			y += 9;
		}
		if(onBackground){
			x += guiLeft;
			y += guiTop;
		}
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
		
		GL11.glTexCoord3f(xMin, yMin, zLevel); // xy
		GL11.glVertex2f(x, y);
		
		GL11.glTexCoord3f(xMin, yMax, zLevel); // xY
		GL11.glVertex2f(x, y+yStep);
		
		GL11.glTexCoord3f(xMax, yMin, zLevel); // Xy
		GL11.glVertex2f(x+xStep, y);
		
		GL11.glTexCoord3f(xMax, yMax, zLevel); // XY
		GL11.glVertex2f(x+xStep, y+yStep);
		
		GL11.glEnd();
		GL11.glEnable(GL11.GL_LIGHTING);
	}
	
	protected void bindImage(ResourceLocation rl){
		mc.renderEngine.func_110577_a(rl);
	}
	
	protected void drawLeft(String s, int x, int y, int color){
		x += 9;
		y += 9;
		fontRenderer.drawString(s, x, y, color);
	}
	
	protected void drawCentered(String s, int xMid, int y, int color){
		drawLeft(s, xMid - fontRenderer.getStringWidth(s) / 2, y, color);
	}
}