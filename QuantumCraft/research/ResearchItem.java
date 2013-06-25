package mods.QuantumCraft.research;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class ResearchItem {
	/**
     * Is the column (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed.
     */
    public final int displayColumn;

    /**
     * Is the row (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed.
     */
    public final int displayRow;
    /**
     * Holds the description of the achievement, ready to be formatted and/or displayed.
     */
    public final String riDescription;
    /**
     * Holds the ItemStack that will be used to draw the achievement into the GUI.
     */
    public final ItemStack theItemStack;

    public final String n;
    
    public String getName()
    {
    	return n;
    }
    
    public ResearchItem(String par2Str, String name, int par3, int par4, ItemStack is)
    {
    	this.n = name;
        this.riDescription = "researchItem." + par2Str + ".desc";
        this.displayColumn = par3;
        this.displayRow = par4;
        this.theItemStack = is;
        if (par3 < ResearchItemList.minDisplayColumn)
        {
        	ResearchItemList.minDisplayColumn = par3;
        }

        if (par4 < ResearchItemList.minDisplayRow)
        {
        	ResearchItemList.minDisplayRow = par4;
        }

        if (par3 > ResearchItemList.maxDisplayColumn)
        {
        	ResearchItemList.maxDisplayColumn = par3;
        }

        if (par4 > ResearchItemList.maxDisplayRow)
        {
        	ResearchItemList.maxDisplayRow = par4;
        }
    }

}
