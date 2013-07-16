package mods.quantumcraft.gui;

import mods.quantumcraft.inventory.ContainerQDeenergizer;
import mods.quantumcraft.inventory.ContainerQEInjector;
import mods.quantumcraft.machine.TileQDeenergizer;
import mods.quantumcraft.machine.TileQEInjector;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiQEInjector  extends GuiContainer {

    private TileQEInjector tile;

    public GuiQEInjector(Container par1Container) {
        super(par1Container);
        tile = ((ContainerQEInjector) par1Container).tile;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = this.tile.getInvName();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString("MAX: " + tile.maxival + "; CUR: " + tile.currentival,
                this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 15, 4210752);
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

        if (tile.maxival != 0) {
            int h = (int) (16 - ((float) tile.currentival / (float) tile.maxival * 16));
            this.drawTexturedModalRect(k + 69, l + 34 + h, 176, 0 + h, 16, 16 - h);
        }


    }

}
