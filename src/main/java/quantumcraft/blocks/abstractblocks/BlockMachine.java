package quantumcraft.blocks.abstractblocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import quantumcraft.core.QuantumCraft;
import quantumcraft.core.interfaces.IUpgradable;
import quantumcraft.core.interfaces.IUpgrade;
import quantumcraft.tile.abstracttiles.TileMachineBase;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.Coords;

public abstract class BlockMachine extends BlockRotatable {
    protected IIcon IconFront;
    protected IIcon IconSide;
    protected IIcon IconBack;
    protected IIcon IconBottom;
    protected IIcon IconTop;
    protected IIcon IconTopR;

    public BlockMachine(int id, Material material) {
        super(id, material);
        setHardness(20F);
        setResistance(10F);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        TileMachineBase tile =
                (TileMachineBase) BasicUtils.getTileEntity(world, new Coords(x, y, z), TileMachineBase.class);
        if (tile != null) {
            tile.onBlockBreak();
        }
        super.breakBlock(world, x, y, z, block, par6);
        world.removeTileEntity(x, y, z);
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side,
                                    float xOffset, float yOffset, float zOffset) {
        TileMachineBase te = (TileMachineBase) world.getTileEntity(x, y, z);

        //PLAYER IS SNEAKING, SHOULD I REMOVE THE BLOCK?
        if (entityplayer.isSneaking() && BasicUtils.isHoldingWrench(entityplayer)) {
            TileMachineBase tile =
                    (TileMachineBase) BasicUtils.getTileEntity(world, new Coords(x, y, z), TileMachineBase.class);
            ItemStack dropItem = new ItemStack(this);
            if (tile != null) {
                dropItem.setItemDamage(tile.getMaxEnergy() - tile.getCurrentEnergy() + 1);
                dropItem.getItem().setMaxDamage(tile.getMaxEnergy() + 1);
            }
            this.dropBlockAsItem(world, x, y, z, dropItem);
            this.removedByPlayer(world, entityplayer, x, y, z);
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

            //PLAYER IS SNEAKING, SHOULD I DROP UPGRADES?
            if (te instanceof IUpgradable) {
                ((IUpgradable) te).dropUpgrades();
                return true;
            }

            //WELL THEN, DO NOTHING <= OMG CAPS LOCK!
            return false;
        }

        //THE PLAYER ISN'T SNEAKING, DOES HE HAVE A WRENCH?
        if (BasicUtils.isHoldingWrench(entityplayer) && te.canRotate()) {
            te.rotate();
            return true;
        }
        if (te.guiID() != -1) {
            entityplayer.openGui(QuantumCraft.instance, te.guiID(), world, x, y, z);
            return true;
        }
        return false;
    }

    public IIcon getIIconFromSide(int side, boolean topAlternative) {
        switch (side) {
            case 0:
                return IconBottom;
            case 1:
                return (topAlternative ? IconTop : IconTopR);
            case 2:
                return IconBack;
            case 3:
                return IconFront;
            case 4:
                return IconSide;
            case 5:
                return IconSide;
            default:
                return Blocks.stone.getIcon(0,0);
        }
    }

    @SideOnly(Side.CLIENT)
    public abstract void registerIIcons(IIconRegister IIconRegister);

    @Override
    public IIcon getIcon(IBlockAccess iblockaccess, int x, int y, int z, int side) {
        TileEntity te = iblockaccess.getTileEntity(x, y, z);
        //if (this.getTileEntity().getClass().isInstance(te)) {
        if (te instanceof TileMachineBase) {
            side = ((TileMachineBase) te).getRotatedSide(side);
        }
        if (te == null) {
            return Blocks.stone.getIcon(0, 0);
        }
        return getIIconFromSide(side, ((TileMachineBase) te).useRotated());
    }

    /*@Override
    public IIcon getIcon(int side, int meta) {
        if (meta == side) {
            return IconFront;
        } else if (side == side - 2) {
            return getIIconFromSide(side - 2, true);
        } else if (side == side - 3) {
            return getIIconFromSide(side - 3, true);
        } else {
            return getIIconFromSide(side, true);
        }
    }
    */

}
