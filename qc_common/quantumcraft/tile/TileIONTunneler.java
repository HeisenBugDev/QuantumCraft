package quantumcraft.tile;

import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.BasicUtils;

public class TileIONTunneler extends TileEnergySink {
    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int guiID() {
        return 7;
    }

    @Override
    public void onBlockBreak() {

    }

    @Override
    public void updateEntity() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(i);
            int location = xCoord + (i * 3);
            if (worldObj.getBlockId(location, yCoord, zCoord) != 0) {
                BasicUtils.getBlockInstance(worldObj, location, yCoord, zCoord)
                        .dropBlockAsItem(worldObj, location, yCoord,
                                zCoord, 1, 1);
            }
            worldObj.setBlockToAir(location, yCoord, zCoord);
        }

        //System.out.println("The coords are: " + xCoord + " " + yCoord + " " + zCoord);
    }
}
