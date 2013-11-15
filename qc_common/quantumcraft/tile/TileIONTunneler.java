package quantumcraft.tile;

import quantumcraft.tile.abstracttiles.TileEnergySink;

public class TileIONTunneler extends TileEnergySink {
    int length = 0;
    int multiplier = 0;

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
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    multiplier = 0;
                    break;
                case 1:
                    multiplier = -3;
                    break;
                case 2:
                    multiplier = 3;
                    break;
            }
            int location = xCoord + (i * multiplier);
            System.out.println("location: " + location + " multiplier: " + multiplier + " i: " + i);
            worldObj.setBlockToAir(location, yCoord, zCoord + length + 1);
        }
        if (length >= 10) length = 0;
        length++;

        //System.out.println("The coords are: " + xCoord + " " + yCoord + " " + zCoord);
    }
}
