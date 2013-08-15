package mods.quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.quantumcraft.blocks.abstractblocks.BlockEnergySink;
import mods.quantumcraft.util.BasicUtils;
import mods.quantumcraft.machine.TileQEnergySucker;
import mods.quantumcraft.machine.abstractmachines.TileMachineBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class BlockQEnergySucker extends BlockEnergySink {

    public BlockQEnergySucker(int id) {
        super(id, Material.iron);
        setHardness(10F);
        setResistance(5F);
    }

    private Icon iconFront;
    private Icon iconSide;
    private Icon iconBack;
    private Icon iconBottom;
    private Icon iconTop;
    private Icon iconTopR;

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileQEnergySucker();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        iconFront = iconRegister.registerIcon("QuantumCraft:machineQES_front");
        iconTop = iconRegister.registerIcon("QuantumCraft:machineQES_top");
        iconTopR = iconRegister.registerIcon("QuantumCraft:machineQES_top_r");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQES_side");
        iconBottom = iconRegister.registerIcon("QuantumCraft:machineQES_bottom");
        iconSide = iconRegister.registerIcon("QuantumCraft:machineQES_side");
        iconBack = iconRegister.registerIcon("QuantumCraft:machineQES_back");
    }

    @Override
    public Icon getBlockTexture(IBlockAccess iblockaccess, int x, int y, int z,
                                int side) {
        TileEntity te = iblockaccess.getBlockTileEntity(x, y, z);
        if (te instanceof TileQEnergySucker) {
            side = ((TileQEnergySucker) te).getRotatedSide(side);
        }
        return getIconFromSide(side, ((TileQEnergySucker) te).useRotated());
    }

    public Icon getIconFromSide(int side) {
        return getIconFromSide(side, false);
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
