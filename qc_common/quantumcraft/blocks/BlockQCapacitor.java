package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.net.IQEnergySource;
import quantumcraft.net.Location;
import quantumcraft.net.QuantumEnergyNet;
import quantumcraft.tile.TileQCapacitor;

public class BlockQCapacitor extends BlockEnergySink implements IQEnergySource{

    public BlockQCapacitor(int id) {
        super(id);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity,
                stack);
        QuantumEnergyNet.propagateSourceLocation(world, new Location(x, y, z));
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        // [todo] - Icons for Quantum Capicator
        // See #22 for info.
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQCapacitor();
    }

    @Override
    public int requestQuantumEnergy(World w, Location l, int request) {
        return ((TileQCapacitor) w.getBlockTileEntity(l.getXCoord(), l.getYCoord(), l.getZCoord()))
                .requestQuantumEnergy(l, request);
    }
}
