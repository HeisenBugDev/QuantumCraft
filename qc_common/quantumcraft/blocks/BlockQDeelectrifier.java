package quantumcraft.blocks;

import quantumcraft.blocks.abstractblocks.BlockEnergySource;
import quantumcraft.tile.TileQDeelectrifier;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 9/2/13
 * Time: 3:09 PM
 */
public class BlockQDeelectrifier extends BlockEnergySource {

    public BlockQDeelectrifier(int id) {
        super(id, Material.iron);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQDL_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQDL_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQDL_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDL_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQDL_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDL_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQDL_back");
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQDeelectrifier();
    }
}
