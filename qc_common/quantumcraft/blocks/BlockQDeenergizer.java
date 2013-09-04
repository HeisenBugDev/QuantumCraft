package quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import quantumcraft.blocks.abstractblocks.BlockEnergySource;
import quantumcraft.tile.TileQDeenergizer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockQDeenergizer extends BlockEnergySource {

    public BlockQDeenergizer(int id) {
        super(id, Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQDeenergizer();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQDE_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQDE_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQDE_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDE_side");
        iconBottom = iconRegister
                .registerIcon("QuantumCraft:machineQDE_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDE_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQDE_back");
    }

}