package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileIONTunneler;

public class BlockIONTunneler extends BlockEnergySink {

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machine_ion_tunneler_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machine_ion_tunneler_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machine_ion_tunneler_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machine_ion_tunneler_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machine_ion_tunneler_bottom");
        iconBack = iconRegister.registerIcon("QuantumCraft:machine_ion_tunneler_back");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileIONTunneler();
    }
}
