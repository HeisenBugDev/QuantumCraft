package quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileQuantumEnergySucker;

public class BlockQuantumEnergySucker extends BlockEnergySink {

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileQuantumEnergySucker();
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQES_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQES_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQES_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQES_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQES_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQES_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQES_back");
    }

}
