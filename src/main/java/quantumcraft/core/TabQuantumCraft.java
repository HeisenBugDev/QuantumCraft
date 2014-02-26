package quantumcraft.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabQuantumCraft extends CreativeTabs {

    public TabQuantumCraft() {
        super("quantumCraft");
    }

    @Override
    public Item getTabIconItem() {
        return Loader.ItemCrystalQuantonium;
    }

    @Override
    public String getTranslatedTabLabel() {
        return "Quantum Craft";
    }
}
