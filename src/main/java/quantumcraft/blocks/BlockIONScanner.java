package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileIONScanner;

public class BlockIONScanner extends BlockEnergySink {
    public BlockIONScanner(int id) {
        super(id);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineIOS_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineIOS_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineIOS_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineIOS_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineIOS_bottom");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineIOS_side");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileIONScanner();
    }
}
