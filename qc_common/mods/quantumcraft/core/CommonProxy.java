package mods.quantumcraft.core;

import cpw.mods.fml.common.network.IGuiHandler;
import mods.quantumcraft.inventory.ContainerQDeenergizer;
import mods.quantumcraft.inventory.ContainerQEInjector;
import mods.quantumcraft.machine.TileQDeenergizer;
import mods.quantumcraft.machine.TileQEInjector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity != null) {
            switch (ID) {
                case 1:
                    return new ContainerQDeenergizer(player.inventory,
                            (TileQDeenergizer) world.getBlockTileEntity(x, y, z));
                case 2:
                    return new ContainerQEInjector(player.inventory,
                            (TileQEInjector) world.getBlockTileEntity(x, y, z));
            }
        }
        return null;
    }
}