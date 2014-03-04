package quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergySink;
import quantumcraft.tile.TileQuantumEnergyInjector;

public class BlockQuantumEnergyInjector extends BlockEnergySink {

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileQuantumEnergyInjector();
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machine_quantum_energy_injector_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machine_quantum_energy_injector_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machine_quantum_energy_injector_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machine_quantum_energy_injector_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machine_quantum_energy_injector_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machine_quantum_energy_injector_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machine_quantum_energy_injector_back");
    }

}