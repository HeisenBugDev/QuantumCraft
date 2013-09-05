package quantumcraft.gui.handlers;

import quantumcraft.gui.GuiQEInjector;
import quantumcraft.gui.abstractguis.GuiBase;

public class HoverHandler implements GuiBase.IHoverHandler {

    int id;
    GuiQEInjector i;

    public HoverHandler(int id, GuiQEInjector i) {
        this.id = id;
        this.i = i;
    }

    @Override
    public void onHover(int x, int y) {
        i.buffHX = x;
        i.buffHY = y;
        i.buffHT[this.id] = true;
    }

    @Override
    public void onLeave() {
        i.buffHT[this.id] = false;
    }
}