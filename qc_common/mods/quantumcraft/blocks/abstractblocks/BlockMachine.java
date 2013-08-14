package mods.quantumcraft.blocks.abstractblocks;

import mods.quantumcraft.core.QuantumCraft;
import mods.quantumcraft.core.interfaces.IUpgradable;
import mods.quantumcraft.util.BasicUtils;
import mods.quantumcraft.core.Coords;
import mods.quantumcraft.machine.abstractmachines.TileMachineBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
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
}
