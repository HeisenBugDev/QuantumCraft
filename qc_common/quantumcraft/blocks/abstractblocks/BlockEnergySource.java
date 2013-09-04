package quantumcraft.blocks.abstractblocks;

import quantumcraft.tile.abstracttiles.TileEnergySource;
import quantumcraft.net.IQEnergySource;
import quantumcraft.net.Location;
import quantumcraft.net.QuantumEnergyNet;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockEnergySource extends BlockEnergyComponent implements IQEnergySource {

    public BlockEnergySource(int id, Material material) {
        super(id, material);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x,y,z,entity,stack);
        QuantumEnergyNet.propagateSourceLocation(world, new Location(x,y,z));
    }

    @Override
    public int getQuantumEnergy(World w, Location l, int request) {
        return ((TileEnergySource)w.getBlockTileEntity(l.getXCoord(),l.getYCoord(),l.getZCoord())).getQuantumEnergy(l, request);
    }
}
