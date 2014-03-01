package quantumcraft.blocks.abstractblocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import quantumcraft.net.EnergySourceList;
import quantumcraft.net.IQEnergySink;
import quantumcraft.net.QuantumEnergyNet;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.Coords;

public abstract class BlockEnergySink extends BlockEnergyComponent implements IQEnergySink {
    public BlockEnergySink() {
        super(Material.iron);
    }

    @Override
    public void addSourceToList(World w, Coords l, Coords source) {
        ((TileEnergySink) w.getTileEntity(l.getXCoord(), l.getYCoord(), l.getZCoord())).addSourceToList(l, source);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        Coords l = new Coords(x, y, z);
        replaceSourceList(world, l, new EnergySourceList(QuantumEnergyNet.getSourceLocation(world, l)));
    }


    @Override
    public void replaceSourceList(World w, Coords l, EnergySourceList sources) {
        ((TileEnergySink) w.getTileEntity(l.getXCoord(), l.getYCoord(), l.getZCoord()))
                .replaceSourceList(l, sources);
    }
}
