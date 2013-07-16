package mods.quantumcraft.core;

import mods.quantumcraft.gui.GuiQDeenergizer;
import mods.quantumcraft.gui.GuiQEInjector;
import mods.quantumcraft.gui.GuiResearch;
import mods.quantumcraft.inventory.ContainerQDeenergizer;
import mods.quantumcraft.inventory.ContainerQEInjector;
import mods.quantumcraft.machine.TileQDeenergizer;
import mods.quantumcraft.machine.TileQEInjector;
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

        }
        return null;

    }
}