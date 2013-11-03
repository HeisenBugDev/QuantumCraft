package quantumcraft.gui;

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
        this.mc.renderEngine.bindTexture(new ResourceLocation("quantumcraft", "textures/gui/deenergizer.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int h2 = (int) (76 - ((float) tile.getCurrentEnergy() / (float) tile.getMaxEnergy() * 76));

        this.drawTexturedModalRect(k + 177, l + 37 + h2, 195, 16 + h2, 12, 76 - h2);
        this.drawTexturedModalRect(k + 177, l + 37, 207, 16, 12, 76);
    }
}
