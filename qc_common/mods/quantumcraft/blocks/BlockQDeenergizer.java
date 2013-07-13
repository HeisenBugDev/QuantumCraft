package mods.quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.quantumcraft.core.BasicUtils;
import mods.quantumcraft.core.Coords;
import mods.quantumcraft.core.QuantumCraft;
import mods.quantumcraft.machine.TileMachineBase;
import mods.quantumcraft.machine.TileQDeenergizer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockQDeenergizer extends BlockRotatable {

    private Icon iconFront;
    private Icon iconSide;
    private Icon iconBack;
    private Icon iconBottom;
    private Icon iconTop;

    public BlockQDeenergizer(int id) {
        super(id, Material.iron);
        setHardness(10F);
        setResistance(5F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQDeenergizer();
    }


    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

        TileMachineBase tile =
                (TileMachineBase) BasicUtils.getTileEntity(world, new Coords(x, y, z), TileMachineBase.class);
        if (tile != null) {
            System.out.println("block was broken");
            tile.onBlockBreak();
        }
        super.breakBlock(world, x, y, z, par5, par6);
        world.removeBlockTileEntity(x, y, z);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQDE_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQDE_top");
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
        return getIconFromSide(side);
    }

    public Icon getIconFromSide(int side) {
        switch (side) {
            case 0:
                return iconBottom;
            case 1:
                return iconTop;
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
            return getIconFromSide(side - 2);
        } else if (side == side - 3) {
            return getIconFromSide(side - 3);
        } else {
            return getIconFromSide(side);
        }

    }

    public boolean onBlockActivated(World world, int x, int y, int z,
                                    EntityPlayer entityPlayer, int par6, float par7, float par8,
                                    float par9) {
        if (!entityPlayer.isSneaking()) {
            entityPlayer.openGui(QuantumCraft.instance, 1, world, x, y, z);
            return true;
        }
        return false;
    }
}