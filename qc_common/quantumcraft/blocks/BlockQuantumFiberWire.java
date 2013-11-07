package quantumcraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import quantumcraft.blocks.abstractblocks.BlockEnergyComponent;
import quantumcraft.render.RenderFiberWire;

public class BlockQuantumFiberWire extends BlockEnergyComponent {
    public static Icon iconTexture;
    public BlockQuantumFiberWire(int id) {
        super(id, Material.circuits);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

    @Override
    public int getRenderType(){
        return RenderFiberWire.instance().getRenderId();
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconTop = iconTopR =
                iconSide = iconBottom = iconBack = iconTexture= iconRegister.registerIcon("QuantumCraft:FibreWireFullBlock");
    }
}
