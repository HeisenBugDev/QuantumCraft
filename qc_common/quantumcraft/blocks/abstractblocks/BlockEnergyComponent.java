package quantumcraft.blocks.abstractblocks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import quantumcraft.net.IQEnergyComponent;
import quantumcraft.net.Location;
import quantumcraft.net.QuantumEnergyNet;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public abstract class BlockEnergyComponent extends BlockMachine implements IQEnergyComponent {

    public BlockEnergyComponent(int id, Material material) {
        super(id, material);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
                                EntityLivingBase entity, ItemStack stack) {

        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        QuantumEnergyNet.onAddedLink(world, new Location(x, y, z));

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        super.breakBlock(world, x, y, z, par5, par6);
        QuantumEnergyNet.onChangedLink(world, getallsides(new Location(x, y, z)));
    }

    public Location[] getallsides(Location l) {
        return new Location[]{new Location(l.getXCoord() - 1, l.getYCoord(), l.getZCoord()), //x-
                new Location(l.getXCoord() + 1, l.getYCoord(), l.getZCoord()),               //x+
                new Location(l.getXCoord(), l.getYCoord() - 1, l.getZCoord()),               //y-
                new Location(l.getXCoord(), l.getYCoord() + 1, l.getZCoord()),               //y+
                new Location(l.getXCoord(), l.getYCoord(), l.getZCoord() - 1),               //z-
                new Location(l.getXCoord(), l.getYCoord(), l.getZCoord() + 1)};              //z+
    }

    @Override
    public Location[] getPossibleConnections(World w, Location l) {
        return getallsides(l);
    }
}
