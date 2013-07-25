package mods.quantumcraft.blocks;

import mods.quantumcraft.blocks.abstractblocks.BlockEnergySink;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/24/13
 * Time: 8:50 PM
 */
public class BlockQDematerializer extends BlockEnergySink {
    public BlockQDematerializer(int id, Material material) {
        super(id, material);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }
}
