package mods.quantumcraft.core;

import mods.quantumcraft.gui.GuiQDeenergizer;
import mods.quantumcraft.gui.GuiResearch;
import mods.quantumcraft.inventory.ContainerQDeenergizer;
import mods.quantumcraft.machine.TileQDeenergizer;
import mods.quantumcraft.research.ResearchHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
		case 0: 
			return new GuiResearch(new ResearchHandler(player));
		case 1:
			TileQDeenergizer te = (TileQDeenergizer) world.getBlockTileEntity(x,y,z);
			return new GuiQDeenergizer(new ContainerQDeenergizer(player.inventory, te));
		}
		return null;

	}
}