package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySource;
import quantumcraft.tile.TileQInterdimensionalGenerator;
import quantumcraft.util.Coords;
import quantumcraft.util.QIGUtil;

public class BlockQInterdimensionalGenerator extends BlockEnergySource {
    public BlockQInterdimensionalGenerator(int id) {
        super(id);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQInterdimensionalGenerator();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        QIGUtil.addGenerator(new Coords(x, y, z));
        QIGUtil.updateAllGenerators(world);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        super.breakBlock(world, x, y, z, par5, par6);
        QIGUtil.removeGeneratorFromNewCoords(new Coords(x, y, z));
    }
}
