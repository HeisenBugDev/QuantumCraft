package quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySource;
import quantumcraft.tile.TileQuantumEnergyExtractor;

public class BlockQuantumEnergyExtractor extends BlockEnergySource {

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileQuantumEnergyExtractor();
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQEE_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQEE_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQEE_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEE_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQEE_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEE_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQEE_back");
    }

}