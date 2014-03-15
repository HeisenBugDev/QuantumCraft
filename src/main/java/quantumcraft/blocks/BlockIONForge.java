package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileIONForge;

public class BlockIONForge extends BlockEnergySink {

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machine_ion_forge_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machine_ion_forge_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machine_ion_forge_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machine_ion_forge_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machine_ion_forge_bottom");
        iconBack = iconRegister.registerIcon("QuantumCraft:machine_ion_forge_back");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileIONForge();
    }
}
