package quantumcraft.blocks.abstractblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import quantumcraft.net.IQEnergyComponent;
import quantumcraft.net.QuantumEnergyNet;
import quantumcraft.tile.abstracttiles.TileMachineBase;
import quantumcraft.util.BasicUtils;
import quantumcraft.util.Coords;

public abstract class BlockEnergyComponent extends BlockMachine implements IQEnergyComponent {

    public BlockEnergyComponent(Material material) {
        super(material);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {

        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        TileMachineBase tile =
                (TileMachineBase) BasicUtils.getTileEntity(world, new Coords(x, y, z), TileMachineBase.class);
        if (stack.getItemDamage() > 0) {
            tile.setEnergy(tile.getMaxEnergy() - stack.getItemDamage());
        }
        QuantumEnergyNet.onAddedLink(world, new Coords(x, y, z));

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        super.breakBlock(world, x, y, z, block, par6);
        QuantumEnergyNet.onChangedLink(world, getallsides(new Coords(x, y, z)));
    }

    public Coords[] getallsides(Coords l) {
        return new Coords[]{new Coords(l.getXCoord() - 1, l.getYCoord(), l.getZCoord()), //x-
                new Coords(l.getXCoord() + 1, l.getYCoord(), l.getZCoord()),               //x+
                new Coords(l.getXCoord(), l.getYCoord() - 1, l.getZCoord()),               //y-
                new Coords(l.getXCoord(), l.getYCoord() + 1, l.getZCoord()),               //y+
                new Coords(l.getXCoord(), l.getYCoord(), l.getZCoord() - 1),               //z-
                new Coords(l.getXCoord(), l.getYCoord(), l.getZCoord() + 1)};              //z+
    }

    @Override
    public Coords[] getPossibleConnections(World w, Coords l) {
        return getallsides(l);
    }

    @Override
    public boolean canFiberConnectOnSide(IBlockAccess w, int x, int y, int z, int side) {
        return true;
    }
}
