package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileIONHarvester;

public class BlockIONHarvester extends BlockEnergySink {

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineIOH_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineIOH_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineIOH_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineIOH_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineIOH_bottom");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineIOH_side");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileIONHarvester();
    }
}
