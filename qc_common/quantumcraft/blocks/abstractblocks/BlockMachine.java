package quantumcraft.blocks.abstractblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import quantumcraft.blocks.BlockQuantumFiberWire;
import quantumcraft.core.Coords;
import quantumcraft.core.QuantumCraft;
import quantumcraft.core.interfaces.IUpgradable;
import quantumcraft.core.interfaces.IUpgrade;
import quantumcraft.tile.abstracttiles.TileMachineBase;
import quantumcraft.util.BasicUtils;

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
        TileMachineBase te = (TileMachineBase) world.getBlockTileEntity(x, y, z);

        //PLAYER IS SNEAKING, SHOULD I REMOVE THE BLOCK?
        if (entityplayer.isSneaking() && BasicUtils.isHoldingWrench(entityplayer)) {
            this.dropBlockAsItem(world, x, y, z, 1, 1);
            this.removeBlockByPlayer(world, entityplayer, x, y, z);
            return true;
        }

        if (te == null) {
            return false;
        }

        if (entityplayer.isSneaking()) {
            //PLAYER IS SNEAKING, DOES HE HAVE AN UPGRADE?
            if ((entityplayer.getCurrentEquippedItem() != null) &&
                    entityplayer.getCurrentEquippedItem().getItem() instanceof IUpgrade && te instanceof IUpgradable) {
                System.out.print("INSTALLING UPGRADE");
                ((IUpgradable) te).eatUpgrade(entityplayer.getCurrentEquippedItem().getItemDamage());
                entityplayer.destroyCurrentEquippedItem();
                return true;
            }

            //PLAYER IS SNEAKING, DOES HE HAVE A WRENCH?
            if (BasicUtils.isHoldingWrench(entityplayer) && te instanceof IUpgradable) {
                ((IUpgradable) te).dropUpgrades();
                return true;
            }

            //WELL THEN, DO NOTHING
            return false;
        }

        //THE PLAYER ISN'T SNEAKING, DOES HE HAVE A WRENCH?
        if (BasicUtils.isHoldingWrench(entityplayer) && te.canRotate()) {
            te.rotate();
            return true;
        }

        entityplayer.openGui(QuantumCraft.instance, te.guiID(), world, x, y, z);
        return true;
    }

    protected Icon iconFront;
    protected Icon iconSide;
    protected Icon iconBack;
    protected Icon iconBottom;
    protected Icon iconTop;
    protected Icon iconTopR;


    public Icon getIconFromSide(int side, boolean topAlternative) {
        switch (side) {
            case 0:
                return iconBottom;
            case 1:
                return (topAlternative ? iconTop : iconTopR);
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
        if (te instanceof TileMachineBase) {
            side = ((TileMachineBase) te).getRotatedSide(side);
        }
        if (te == null) {
            return Block.stone.getIcon(0, 0);
        }
        return getIconFromSide(side, ((TileMachineBase) te).useRotated());
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
