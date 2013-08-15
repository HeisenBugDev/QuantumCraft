package mods.quantumcraft.core;

import mods.quantumcraft.gui.*;
import mods.quantumcraft.inventory.*;
import mods.quantumcraft.machine.*;
import mods.quantumcraft.research.ResearchHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                return new GuiResearch(new ResearchHandler(player));
            case 1:
                TileQDeenergizer te1 = (TileQDeenergizer) world.getBlockTileEntity(x, y, z);
                return new GuiQDeenergizer(new ContainerQDeenergizer(player.inventory, te1));
            case 2:
                TileQEInjector te2 = (TileQEInjector) world.getBlockTileEntity(x, y, z);
                return new GuiQEInjector(new ContainerQEInjector(player.inventory, te2));
            case 3:
                TileQDislocator te3 = (TileQDislocator) world.getBlockTileEntity(x, y, z);
                return new GuiQDislocator(new ContainerQDislocator(player.inventory, te3));
            case 4:
                TileQDematerializer te4 = (TileQDematerializer) world.getBlockTileEntity(x, y, z);
                return new GuiQDematerializer(new ContainerQDematerializer(player.inventory, te4));
            case 5:
                TileQEExtractor te5 = (TileQEExtractor) world.getBlockTileEntity(x, y, z);
                return new GuiQEExtractor(new ContainerQEExtractor(player.inventory, te5));
        }
        return null;

    }
}