package mods.quantumcraft.blocks.abstractblocks;

import mods.quantumcraft.machine.abstractmachines.TileEnergySource;
import mods.quantumcraft.net.IQEnergySource;
import mods.quantumcraft.net.Location;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public abstract class BlockEnergySource extends BlockEnergyComponent implements IQEnergySource {

    public BlockEnergySource(int id, Material material) {
        super(id, material);
    }

    @Override
    public int getQuantumEnergy(World w, Location l, int request) {
        return ((TileEnergySource)w.getBlockTileEntity(l.getXCoord(),l.getYCoord(),l.getZCoord())).getQuantumEnergy(l, request);
    }
}
