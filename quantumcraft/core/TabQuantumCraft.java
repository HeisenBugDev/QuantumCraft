package mods.quantumcraft.core;

import net.minecraft.creativetab.CreativeTabs;

public class TabQuantumCraft extends CreativeTabs {

    public TabQuantumCraft() {
        super("quantumCraft");
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getTabIconItemIndex() {
        return Loader.ItemCrystalQuantonium.itemID;
    }

    @Override
    public String getTranslatedTabLabel() {
        return "Quantum Craft";
    }
}
