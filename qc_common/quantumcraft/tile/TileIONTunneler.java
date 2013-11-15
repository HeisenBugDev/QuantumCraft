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
        for (int y = 0; y < 2; y++) {
            for (int i = 0; i < 3; i++) {
                switch (i) {
                    case 0:
                        multiplier = 0;
                        break;
                    case 1:
                        multiplier = -4;
                        break;
                    case 2:
                        multiplier = 4;
                        break;
                }
                int location = xCoord + multiplier;
                worldObj.setBlockToAir(location, yCoord + y, zCoord + length + 1);
            }

        }
        if (length >= 10) length = 0;
        length++;


        //System.out.println("The coords are: " + xCoord + " " + yCoord + " " + zCoord);
    }
}
