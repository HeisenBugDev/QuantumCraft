package mods.quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.quantumcraft.blocks.abstractblocks.BlockMachine;
import mods.quantumcraft.tile.TileQDislocator;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockQDislocator extends BlockMachine {


    public BlockQDislocator(int id) {
        super(id, Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQDislocator();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQDS_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQDS_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQDS_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDS_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQDS_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQDS_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQDS_back");
    }

}