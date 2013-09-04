package quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import quantumcraft.blocks.abstractblocks.BlockEnergySource;
import quantumcraft.tile.TileQDematerializer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/24/13
 * Time: 8:50 PM
 */
public class BlockQDematerializer extends BlockEnergySource {


    public BlockQDematerializer(int id) {
        super(id, Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQDematerializer();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
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
