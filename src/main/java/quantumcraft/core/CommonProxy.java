package quantumcraft.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.inventory.*;
import quantumcraft.tile.*;

public class CommonProxy implements IGuiHandler {
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            switch (ID) {
                case 1:
                    return new ContainerQuantumDeenergizer(player.inventory,
                            (TileQuantumDeenergizer) world.getTileEntity(x, y, z));
                case 2:
                    return new ContainerQuantumEnergyInjector(player.inventory,
                            (TileQuantumEnergyInjector) world.getTileEntity(x, y, z));
                case 4:
                    return new ContainerQuantumDematerializer(player.inventory,
                            (TileQuantumDematerializer) world.getTileEntity(x, y, z));
                case 5:
                    return new ContainerQuantumEnergyExtractor(player.inventory,
                            (TileQuantumEnergyExtractor) world.getTileEntity(x, y, z));
                case 6:
                    return new ContainerIONForge(player.inventory,
                            (TileIONForge) world.getTileEntity(x, y, z));
                case 7:
                    return new ContainerQuantumCapacitor(player.inventory,
                            (TileQuantumCapacitor) world.getTileEntity(x, y, z));
            }
        } return null;
    }
}