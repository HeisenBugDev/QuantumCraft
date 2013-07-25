package mods.quantumcraft.blocks.abstractblocks;

import mods.quantumcraft.core.BasicUtils;
import mods.quantumcraft.core.Coords;
import mods.quantumcraft.machine.abstractmachines.TileMachineBase;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/13/13
 * Time: 6:03 PM
 */
public abstract class BlockMachine extends BlockRotatable {
    public BlockMachine(int id, Material material) {
        super(id, material);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

        TileMachineBase tile =
                (TileMachineBase) BasicUtils.getTileEntity(world, new Coords(x, y, z), TileMachineBase.class);
        if (tile != null) {
            tile.onBlockBreak();
        }
        super.breakBlock(world, x, y, z, par5, par6);
        world.removeBlockTileEntity(x, y, z);
    }
}
