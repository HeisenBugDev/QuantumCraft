package quantumcraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockMachine;
import quantumcraft.tile.TileSteamGenerator;

public class BlockSteamGenerator extends BlockMachine{

    public BlockSteamGenerator(int id) {
        super(id, Material.iron);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        //TODO icons!
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileSteamGenerator();
    }
}