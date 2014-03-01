package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileIONTunneler;

public class BlockIONTunneler extends BlockEnergySink {

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineIOT_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineIOT_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineIOT_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineIOT_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineIOT_bottom");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineIOT_back");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileIONTunneler();
    }
}
