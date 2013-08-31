package mods.quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.quantumcraft.blocks.abstractblocks.BlockEnergySource;
import mods.quantumcraft.machine.TileQDeenergizer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockQDeenergizer extends BlockEnergySource {

    private Icon iconFront;
    private Icon iconSide;
    private Icon iconBack;
    private Icon iconBottom;
    private Icon iconTop;
    private Icon iconTopR;

    public BlockQDeenergizer(int id) {
        super(id, Material.iron);
        setHardness(10F);
        setResistance(5F);
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

    @Override
    public Icon getBlockTexture(IBlockAccess iblockaccess, int x, int y, int z,
                                int side) {
        TileEntity te = iblockaccess.getBlockTileEntity(x, y, z);
        if (te instanceof TileQDeenergizer) {
            side = ((TileQDeenergizer) te).getRotatedSide(side);
        }
        return getIconFromSide(side, ((TileQDeenergizer) te).useRotated());
    }

    public Icon getIconFromSide(int side, boolean talt) {
        switch (side) {
            case 0:
                return iconBottom;
            case 1:
                return (talt ? iconTop : iconTopR);
            case 2:
                return iconBack;
            case 3:
                return iconFront;
            case 4:
                return iconSide;
            case 5:
                return iconSide;
            default:
                return Block.stone.getIcon(0, 0);
        }
    }

    @Override
    public Icon getIcon(int side, int meta) {
        if (meta == side) {
            return iconFront;
        } else if (side == side - 2) {
            return getIconFromSide(side - 2, true);
        } else if (side == side - 3) {
            return getIconFromSide(side - 3, true);
        } else {
            return getIconFromSide(side, true);
        }

    }
}