package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileIONForge;

public class BlockIONForge extends BlockEnergySink {
    public BlockIONForge(int id) {
        super(id);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineIOF_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineIOF_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineIOF_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineIOF_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineIOF_bottom");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineIOF_back");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileIONForge();
    }
}
