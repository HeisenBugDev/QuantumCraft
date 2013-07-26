package mods.quantumcraft.blocks;

import mods.quantumcraft.blocks.abstractblocks.BlockEnergySource;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/26/13
 * Time: 8:57 AM
 */
public class BlockQElectrifier extends BlockEnergySource{
    public BlockQElectrifier(int id) {
        super(id, Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }
}
