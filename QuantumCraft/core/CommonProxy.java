package mods.QuantumCraft.core;

import mods.QuantumCraft.inventory.ContainerQDeenergizer;
import mods.QuantumCraft.machine.TileQDeenergizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if(tileEntity != null)
		{
			switch(ID)
			{
			case 1:
				return new ContainerQDeenergizer(player.inventory, (TileQDeenergizer) world.getBlockTileEntity(x, y, z));
			}
		}
		return null;
	}
}