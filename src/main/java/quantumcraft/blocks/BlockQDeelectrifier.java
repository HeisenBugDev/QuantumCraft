package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySource;
import quantumcraft.tile.TileQDeelectrifier;

public class BlockQDeelectrifier extends BlockEnergySource {

    public BlockQDeelectrifier(int id) {
        super(id);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQDL_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQDL_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQDL_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDL_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQDL_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDL_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQDL_back");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileQDeelectrifier();
    }
}
