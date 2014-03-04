package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileIONHarvester;

public class BlockIONHarvester extends BlockEnergySink {

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machine_ion_harvester_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machine_ion_harvester_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machine_ion_harvester_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machine_ion_harvester_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machine_ion_harvester_bottom");
        iconBack = iconRegister.registerIcon("QuantumCraft:machine_ion_harvester_side");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileIONHarvester();
    }
}
