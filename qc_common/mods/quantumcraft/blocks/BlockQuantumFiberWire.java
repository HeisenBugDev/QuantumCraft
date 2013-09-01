package mods.quantumcraft.blocks;

import mods.quantumcraft.blocks.abstractblocks.BlockEnergyComponent;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockQuantumFiberWire extends BlockEnergyComponent {
    public BlockQuantumFiberWire(int id) {
        super(id, Material.circuits);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

    }
}
