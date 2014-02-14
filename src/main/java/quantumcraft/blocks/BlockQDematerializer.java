package quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySource;
import quantumcraft.tile.TileQDematerializer;

public class BlockQDematerializer extends BlockEnergySource {


    public BlockQDematerializer(int id) {
        super(id);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileQDematerializer();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQDM_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQDM_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQDM_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDM_side");
        iconBottom = iconRegister
                .registerIcon("QuantumCraft:machineQDM_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDM_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQDM_back");
    }

}
