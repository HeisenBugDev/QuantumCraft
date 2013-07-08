package mods.quantumcraft.research;

import mods.quantumcraft.core.Loader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.LinkedList;

public class ResearchHandler {

    ResearchEntityProperties rep;
    EntityPlayer player;

    public ResearchHandler(EntityPlayer p) {
        this.player = p;
        rep = (ResearchEntityProperties) p.getExtendedProperties("QC_Research");
        if (rep == null) {
            p.registerExtendedProperties("QC_Research",
                    new ResearchEntityProperties());
            rep = (ResearchEntityProperties) p
                    .getExtendedProperties("QC_Research");
        }

        ris.add(new ResearchItem("Quantonium Ore", "Mine Quantonium Ore", 0, 0,
                -2, new ItemStack(Loader.OreQuantonium), null));
        ris.add(new ResearchItem("Raw Quantonium", "Pick up Raw Quantonium", 1,
                0, 0, new ItemStack(Loader.ItemRawQuantonium), ris.get(0)));
        ris.add(new ResearchItem("Crystallized Quantonium",
                "Smelt your Raw Quantonium", 2, 2, 0, new ItemStack(
                Loader.ItemCrystalQuantonium), ris.get(1)));
        // rep.setUnlocked(0);
    }

    public LinkedList<ResearchItem> ris = new LinkedList<ResearchItem>();

    public boolean hasRIUnlocked(ResearchItem ri) {
        return rep.getUnlocked(ri.index);
    }

    public boolean canUnlockRI(ResearchItem ri) {
        if (ri.parentAchievement != null) {
            return hasRIUnlocked(ri.parentAchievement) ? true : false;
        }
        return true;
    }

}
