package mods.QuantumCraft.core;

import mods.QuantumCraft.gui.GuiQDeenergizer;
import mods.QuantumCraft.gui.GuiResearch;
import mods.QuantumCraft.inventory.ContainerQDeenergizer;
import mods.QuantumCraft.machine.TileQDeenergizer;
import mods.QuantumCraft.research.ResearchHandler;
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