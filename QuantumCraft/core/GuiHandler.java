package mods.QuantumCraft.core;

import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		
		Object o = world.getBlockTileEntity(x, y, z);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		
		Object o = world.getBlockTileEntity(x, y, z);		
		if (ID == 0) { return new GuiIngameMenu(); }
		
		return null;
	}

}
