package mods.quantumcraft.blocks.abstractblocks;

import mods.quantumcraft.net.IQEnergyComponent;
import mods.quantumcraft.net.Location;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public abstract class BlockEnergyComponent extends BlockMachine implements IQEnergyComponent {

    public BlockEnergyComponent(int id, Material material) {
        super(id, material);
    }

    @Override
    public Location[] getPossibleConnections(World w, Location l) {
        return new Location[]{new Location(l.getXCoord()-1, l.getYCoord(), l.getZCoord()), //x-
                new Location(l.getXCoord()+1, l.getYCoord(), l.getZCoord()),               //x+
                new Location(l.getXCoord(), l.getYCoord()-1, l.getZCoord()),               //y-
                new Location(l.getXCoord(), l.getYCoord()+1, l.getZCoord()),               //y+
                new Location(l.getXCoord(), l.getYCoord(), l.getZCoord()-1),               //z-
                new Location(l.getXCoord(), l.getYCoord(), l.getZCoord()+1)};              //z+
    }
}
