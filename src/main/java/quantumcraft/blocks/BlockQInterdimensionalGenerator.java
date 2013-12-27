package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySource;
import quantumcraft.tile.TileQInterdimensionalGenerator;
import quantumcraft.util.Coords;
import quantumcraft.util.QInterdimensionalGeneratorDataObject;
import quantumcraft.util.QInterdimensionalGeneratorUtil;

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
        QInterdimensionalGeneratorUtil
                .addGenerator(new QInterdimensionalGeneratorDataObject(world, new Coords(x, y, z)));
        QInterdimensionalGeneratorUtil.updateAllGenerators();
    }
}
