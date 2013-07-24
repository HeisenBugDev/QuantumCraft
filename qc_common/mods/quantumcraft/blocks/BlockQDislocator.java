package mods.quantumcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.quantumcraft.blocks.abstractblocks.BlockMachine;
import mods.quantumcraft.core.BasicUtils;
import mods.quantumcraft.core.QuantumCraft;
import mods.quantumcraft.machine.abstractmachines.TileMachineBase;
import mods.quantumcraft.machine.TileQDislocator;
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

public class BlockQDislocator extends BlockMachine {

    private Icon iconFront;
    private Icon iconSide;
    private Icon iconBack;
    private Icon iconBottom;
    private Icon iconTop;
    private Icon iconTopR;

    public BlockQDislocator(int id) {
        super(id, Material.iron);
        setHardness(10F);
        setResistance(5F);
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

    @Override
    public Icon getBlockTexture(IBlockAccess iblockaccess, int x, int y, int z,
                                int side) {
        TileEntity te = iblockaccess.getBlockTileEntity(x, y, z);
        if (te instanceof TileQDislocator) {
            side = ((TileQDislocator) te).getRotatedSide(side);
        }
        return getIconFromSide(side,((TileQDislocator) te).useRotated());
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

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side,
                                    float xOffset, float yOffset, float zOffset) {
        PlayerInteractEvent e =
                new PlayerInteractEvent(entityplayer, PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK, x, y, z, side);
        if (MinecraftForge.EVENT_BUS.post(e) || e.getResult() == Event.Result.DENY || e.useBlock == Event.Result.DENY) {
            return false;
        }
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te == null) {
            return false;
        }
        if (BasicUtils.isHoldingWrench(entityplayer) && te instanceof TileMachineBase &&
                ((TileMachineBase) te).canRotate()) {
            ((TileMachineBase) te).rotate();
            world.markBlockForUpdate(x, y, z);
            return true;
        } else if (te instanceof TileMachineBase) {
            if (!world.isRemote) {
                entityplayer.openGui(QuantumCraft.instance, 3, world, x, y, z);
            }
            return true;
        }

        return false;
    }
}