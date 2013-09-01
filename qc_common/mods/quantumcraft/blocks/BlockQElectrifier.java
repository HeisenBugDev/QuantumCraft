package mods.quantumcraft.blocks;

import mods.quantumcraft.blocks.abstractblocks.BlockEnergySink;
import mods.quantumcraft.machine.TileQElectrifier;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/26/13
 * Time: 8:57 AM
 */
public class BlockQElectrifier extends BlockEnergySink {
    public BlockQElectrifier(int id) {
        super(id, Material.iron);
        setHardness(10.0F);
        setResistance(5.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQElectrifier();
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

    }
}
