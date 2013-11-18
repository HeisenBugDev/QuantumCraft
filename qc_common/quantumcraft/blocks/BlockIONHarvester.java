package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileIONHarvester;

public class BlockIONHarvester extends BlockEnergySink {
    public BlockIONHarvester(int id) {
        super(id);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileIONHarvester();
    }
}
