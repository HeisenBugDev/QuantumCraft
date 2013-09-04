package quantumcraft.gui;

import quantumcraft.inventory.ContainerQDematerializer;
import quantumcraft.tile.TileQDematerializer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiQDematerializer extends GuiContainer {

    private TileQDematerializer tile;

    public GuiQDematerializer(Container par1Container) {
        super(par1Container);
        tile = ((ContainerQDematerializer) par1Container).tile;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.xSize = 176+19;
        this.mc.thePlayer.openContainer = this.inventorySlots;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = this.tile.getInvName();
        this.fontRenderer.drawString(s, (this.xSize-19) / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        //this.fontRenderer.drawString("LIV: " + tile.lastItemValue + "; CBV: " + tile.QEnergyItemBuffer,
        //        this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 15, 4210752);
        this.fontRenderer
                .drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //this.mc.renderEngine.func_110577_a("/mods/QuantumCraft/textures/gui/deenergizer.png");
        this.mc.renderEngine.bindTexture(new ResourceLocation("quantumcraft", "textures/gui/dematerializer.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int h2 = (int) (76 - ((float) tile.getCurrentEnergy() / (float) tile.getMaxEnergy() * 76));

        this.drawTexturedModalRect(k + 177, l + 37 + h2, 195, 16 + h2, 12, 76 - h2);
        this.drawTexturedModalRect(k + 177, l + 37, 207, 16, 12, 76);
        if (tile.processTime > 0) {
            int h = (int) (16 - ((float) tile.processTime / (float) 40 * 16));
            this.drawTexturedModalRect(k + 101, l + 34 + h, 195, 0 + h, 16, 16 - h);
        }


    }

}
