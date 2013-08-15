package mods.quantumcraft.gui;

import mods.quantumcraft.core.Loader;
import mods.quantumcraft.gui.abstractguis.GuiBase;
import mods.quantumcraft.inventory.ContainerQDeenergizer;
import mods.quantumcraft.inventory.ContainerQEInjector;
import mods.quantumcraft.machine.TileQDeenergizer;
import mods.quantumcraft.machine.TileQEInjector;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

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

    protected void drawForeground(){
        bindImage(GuiTextures.GUI_BTN_CLOSE);
        drawQuad(189, 9, 0, 1, 0, 1, 9, 9);
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
