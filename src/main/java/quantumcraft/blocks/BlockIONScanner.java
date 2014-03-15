package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileIONScanner;

public class BlockIONScanner extends BlockEnergySink {

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machine_ion_scanner_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machine_ion_scanner_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machine_ion_scanner_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machine_ion_scanner_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machine_ion_scanner_bottom");
        iconBack = iconRegister.registerIcon("QuantumCraft:machine_ion_scanner_side");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileIONScanner();
    }
}
