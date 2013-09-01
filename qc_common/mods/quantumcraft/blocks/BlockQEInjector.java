package mods.quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.quantumcraft.blocks.abstractblocks.BlockEnergySink;
import mods.quantumcraft.machine.TileQEInjector;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockQEInjector extends BlockEnergySink {
    public BlockQEInjector(int id) {
        super(id, Material.iron);
        setHardness(10F);
        setResistance(5F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQEInjector();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQEI_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQEI_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQEI_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEI_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQEI_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQEI_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQEI_back");
    }

}