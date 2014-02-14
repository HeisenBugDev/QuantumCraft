package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileQElectrifier;

public class BlockQElectrifier extends BlockEnergySink {
    public BlockQElectrifier(int id) {
        super(id);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileQElectrifier();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQEL_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQEL_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQEL_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEL_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQEL_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEL_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQEL_back");
    }
}
