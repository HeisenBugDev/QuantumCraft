package quantumcraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileQElectrifier;

public class BlockQElectrifier extends BlockEnergySink {
    public BlockQElectrifier(int id) {
        super(id, Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQElectrifier();
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQEL_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQEL_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQEL_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEL_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQEL_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEL_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQEL_back");
    }
}
