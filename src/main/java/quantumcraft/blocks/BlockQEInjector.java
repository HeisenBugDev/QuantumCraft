package quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileQEInjector;

public class BlockQEInjector extends BlockEnergySink {
    public BlockQEInjector(int id) {
        super(id);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileQEInjector();
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQEI_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQEI_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQEI_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEI_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQEI_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEI_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQEI_back");
    }

}