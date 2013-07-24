package mods.quantumcraft.blocks.abstractblocks;

import mods.quantumcraft.machine.abstractmachines.TileEnergySink;
import mods.quantumcraft.net.EnergySourceList;
import mods.quantumcraft.net.IQEnergySink;
import mods.quantumcraft.net.Location;
import mods.quantumcraft.net.QuantumEnergyNet;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockEnergySink extends BlockEnergyComponent implements IQEnergySink {
    public BlockEnergySink(int id, Material material) {
        super(id, material);
    }

    @Override
    public void addSourceToList(World w, Location l, Location source) {
        ((TileEnergySink) w.getBlockTileEntity(l.getXCoord(), l.getYCoord(), l.getZCoord())).addSourceToList(l, source);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x,y,z,entity,stack);
        Location l = new Location(x,y,z);
        replaceSourceList(world, l, new EnergySourceList(QuantumEnergyNet.getSourceLocations(world, l)));
    }

    @Override
    public void replaceSourceList(World w, Location l, EnergySourceList sources) {
        ((TileEnergySink) w.getBlockTileEntity(l.getXCoord(), l.getYCoord(), l.getZCoord())).replaceSourceList(l, sources);
    }
}
