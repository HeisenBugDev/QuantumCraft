package quantumcraft.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySource;
import quantumcraft.tile.TileQuantumInterdimensionalGenerator;
import quantumcraft.util.Coords;
import quantumcraft.util.QuantumInterdimensionalGeneratorDataObject;
import quantumcraft.util.QuantumInterdimensionalGeneratorUtil;

public class BlockQuantumInterdimensionalGenerator extends BlockEnergySource {

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileQuantumInterdimensionalGenerator();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        QuantumInterdimensionalGeneratorUtil
                .addGenerator(new QuantumInterdimensionalGeneratorDataObject(new Coords(x, y, z)));
        QuantumInterdimensionalGeneratorUtil.updateAllGenerators(world);
    }
}
