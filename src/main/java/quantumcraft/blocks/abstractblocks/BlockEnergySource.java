package quantumcraft.blocks.abstractblocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import quantumcraft.net.IQEnergySource;
import quantumcraft.net.QuantumEnergyNet;
import quantumcraft.tile.abstracttiles.TileEnergySource;
import quantumcraft.util.Coords;

public abstract class BlockEnergySource extends BlockEnergyComponent implements IQEnergySource {

    public BlockEnergySource(int id) {
        super(id, Material.iron);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        QuantumEnergyNet.propagateSourceLocation(world, new Coords(x, y, z));
    }

    @Override
    public int requestQuantumEnergy(World w, Coords l, int request) {
        return ((TileEnergySource) w.getBlockTileEntity(l.getXCoord(), l.getYCoord(), l.getZCoord()))
                .requestQuantumEnergy(l, request);
    }
}
