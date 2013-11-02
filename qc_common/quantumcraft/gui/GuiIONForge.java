package quantumcraft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import quantumcraft.inventory.ContainerIONForge;
import quantumcraft.tile.TileIONForge;

public class GuiIONForge extends GuiContainer {
    private TileIONForge tile;

    public GuiIONForge(Container par1Container) {
        super(par1Container);
        tile = ((ContainerIONForge) par1Container).tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {

    }

    @Override
    public void initGui() {
        super.initGui();
        this.xSize = 176 + 19;
        this.mc.thePlayer.openContainer = this.inventorySlots;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }
}
