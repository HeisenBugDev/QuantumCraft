package quantumcraft.gui;

import net.minecraft.inventory.Container;
import quantumcraft.gui.abstractguis.GuiBase;
import quantumcraft.inventory.ContainerSteamGenerator;
import quantumcraft.tile.TileSteamGenerator;

public class GuiSteamGenerator extends GuiBase {

    public GuiSteamGenerator(Container container) {
        super(container, 200, 170);
        tile = ((ContainerSteamGenerator) container).tile;
    }

    @Override protected void handleClick(int buffCT) {
        if (buffCT > -1) {
            switch (buffCT) {
                case 0:
                    this.mc.thePlayer.closeScreen();
            }
        }
    }
}
