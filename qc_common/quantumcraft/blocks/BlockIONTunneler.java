package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileIONForge;

public class BlockIONTunneler extends BlockEnergySink {
    public BlockIONTunneler(int id) {
        super(id);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineIOT_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineIOT_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineIOT_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineIOT_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineIOT_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineIOT_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineIOT_back");
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileIONForge();
    }
}
