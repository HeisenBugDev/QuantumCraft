package quantumcraft.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import quantumcraft.gui.*;
import quantumcraft.inventory.*;
import quantumcraft.tile.*;

public class ClientProxy extends CommonProxy {
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 1:
                TileQuantumDeenergizer te1 = (TileQuantumDeenergizer) world.getTileEntity(x, y, z);
                return new GuiQuantumDeenergizer(new ContainerQuantumDeenergizer(player.inventory, te1));
            case 2:
                TileQuantumEnergyInjector te2 = (TileQuantumEnergyInjector) world.getTileEntity(x, y, z);
                return new GuiQuantumEnergyInjector(new ContainerQuantumEnergyInjector(player.inventory, te2));
            case 4:
                TileQuantumDematerializer te4 = (TileQuantumDematerializer) world.getTileEntity(x, y, z);
                return new GuiQuantumDematerializer(new ContainerQuantumDematerializer(player.inventory, te4));
            case 5:
                TileQuantumEnergyExtractor te5 = (TileQuantumEnergyExtractor) world.getTileEntity(x, y, z);
                return new GuiQuantumEnergyExtractor(new ContainerQuantumEnergyExtractor(player.inventory, te5));
            case 6:
                TileIONForge te6 = (TileIONForge) world.getTileEntity(x, y, z);
                return new GuiIONForge(new ContainerIONForge(player.inventory, te6));
            case 7:
                TileQuantumCapacitor te7 = (TileQuantumCapacitor) world.getTileEntity(x,y,z);
                return new GuiQuantumCapacitor(new ContainerQuantumCapacitor(player.inventory, te7));
        }
        return null;

    }
}