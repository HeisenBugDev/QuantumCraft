package quantumcraft.blocks.abstractblocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import quantumcraft.net.EnergySourceList;
import quantumcraft.net.IQEnergySink;
import quantumcraft.util.Location;
import quantumcraft.net.QuantumEnergyNet;
import quantumcraft.tile.abstracttiles.TileEnergySink;

public abstract class BlockEnergySink extends BlockEnergyComponent implements IQEnergySink {
    public BlockEnergySink(int id) {
        super(id, Material.iron);
    }

    @Override
    public void addSourceToList(World w, Location l, Location source) {
        ((TileEnergySink) w.getBlockTileEntity(l.getXCoord(), l.getYCoord(), l.getZCoord())).addSourceToList(l, source);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        Location l = new Location(x, y, z);
        replaceSourceList(world, l, new EnergySourceList(QuantumEnergyNet.getSourceLocations(world, l)));
    }


    @Override
    public void replaceSourceList(World w, Location l, EnergySourceList sources) {
        ((TileEnergySink) w.getBlockTileEntity(l.getXCoord(), l.getYCoord(), l.getZCoord()))
                .replaceSourceList(l, sources);
    }
}
