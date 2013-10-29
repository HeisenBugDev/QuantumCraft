package quantumcraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;

public class BlockIONForge extends BlockEnergySink {
    public BlockIONForge(int id, Material material) {
        super(id, Material.iron);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineIONForge_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineIONForge_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineIONForge_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineIONForge_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineIONForge_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineIONForge_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineIONForge_back");
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null; //TODO Setup TE
    }
}
