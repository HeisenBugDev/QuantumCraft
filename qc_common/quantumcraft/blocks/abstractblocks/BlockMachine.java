package quantumcraft.blocks.abstractblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import quantumcraft.core.QuantumCraft;
import quantumcraft.core.interfaces.IUpgradable;
import quantumcraft.util.BasicUtils;
import quantumcraft.core.Coords;
import quantumcraft.tile.abstracttiles.TileMachineBase;
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

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/13/13
 * Time: 6:03 PM
 */
public abstract class BlockMachine extends BlockRotatable {
    public BlockMachine(int id, Material material) {
        super(id, material);
        setHardness(10F);
        setResistance(5F);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

        TileMachineBase tile =
                (TileMachineBase) BasicUtils.getTileEntity(world, new Coords(x, y, z), TileMachineBase.class);
        if (tile != null) {
            tile.onBlockBreak();
        }
        super.breakBlock(world, x, y, z, par5, par6);
        world.removeBlockTileEntity(x, y, z);
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
        if (BasicUtils.isHoldingUpgrade(entityplayer) && te instanceof IUpgradable) { //UPGRADING
            if (((IUpgradable)te).eatUpgrade(entityplayer.getCurrentEquippedItem().getItemDamage())) {
            entityplayer.getCurrentEquippedItem().stackSize--;
            if (entityplayer.getCurrentEquippedItem().stackSize < 1) entityplayer.destroyCurrentEquippedItem();}
        } else
        if (BasicUtils.isHoldingWrench(entityplayer) && te instanceof TileMachineBase && //MULTITOOL HANDLING
                ((TileMachineBase) te).canRotate()) {
            if (entityplayer.isSneaking() && te instanceof IUpgradable) {
                ((IUpgradable)te).dropUpgrades();
            }
            ((TileMachineBase) te).rotate();
            world.markBlockForUpdate(x, y, z);
            return true;
        } else
        if (te instanceof TileMachineBase && (!world.isRemote && !entityplayer.isSneaking())) {  //GUI HANDLING
            entityplayer.openGui(QuantumCraft.instance, ((TileMachineBase)te).guiID(), world, x, y, z);
            return true;
        }
        return false;
    }

    public Icon getIconFromSide(int side) {
        return getIconFromSide(side, false);
    }

    protected Icon iconFront;
    protected Icon iconSide;
    protected Icon iconBack;
    protected Icon iconBottom;
    protected Icon iconTop;
    protected Icon iconTopR;


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

    @SideOnly(Side.CLIENT)
    public abstract void registerIcons(IconRegister iconRegister);

    @Override
    public Icon getBlockTexture(IBlockAccess iblockaccess, int x, int y, int z,
                                int side) {
        TileEntity te = iblockaccess.getBlockTileEntity(x, y, z);
        //if (this.getTileEntity().getClass().isInstance(te)) {
        if (te instanceof TileMachineBase){
            side = ((TileMachineBase) te).getRotatedSide(side);
        }
        return getIconFromSide(side,((TileMachineBase) te).useRotated());
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
