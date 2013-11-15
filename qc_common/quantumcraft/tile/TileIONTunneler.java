package quantumcraft.tile;

import quantumcraft.tile.abstracttiles.TileEnergySink;

public class TileIONTunneler extends TileEnergySink {
    int length = 0;
    int shifter = 0;
    boolean stop = false;
    int pause = 0;

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

    public void dig() {
        int y = yCoord;
        int x = xCoord;
        int z = zCoord;
        int location = 0;
        int useLength = length;
        if (pause > 5) {
            if (!stop) {
                for (int yloop = 0; yloop < 2; yloop++) {
                    for (int mloop = 1; mloop < 5; mloop++) {
                        for (int i = 0; i < 3; i++) {
                            switch (i) {
                                case 0:
                                    shifter = 0;
                                    break;
                                case 1:
                                    shifter = -4;
                                    break;
                                case 2:
                                    shifter = 4;
                                    break;
                            }
                            shifter = shifter * mloop;
                            location = x + shifter;
                            switch (this.getDirectionFacing()) {
                                case NORTH:
                                    shifter = -shifter;
                                    useLength = -length - 2;
                                    location = x + shifter;
                                    break;
                                case EAST:
                                    useLength = shifter - 1;
                                    location = (length + x) + 1;
                                    break;
                                case WEST:
                                    useLength = shifter - 1;
                                    location = ((x - length) - 1);
                                    break;
                            }
                            worldObj.setBlockToAir(location, yCoord + yloop, z + useLength + 1);
                        }
                    }
                }
            }
            if (length >= 50) {
                length = 0;
                stop = true;
            }
            length++;
            pause = 0;
        }
        pause++;
    }

    @Override
    public void updateEntity() {
        dig();
    }
}
