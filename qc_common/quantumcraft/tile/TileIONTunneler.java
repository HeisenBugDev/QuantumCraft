package quantumcraft.tile;

import quantumcraft.tile.abstracttiles.TileEnergySink;

public class TileIONTunneler extends TileEnergySink {
    int length = 0;
    int multiplier = 0;
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

    @Override
    public void updateEntity() {
        int y = yCoord;
        int x = xCoord;
        int z = zCoord;
        int location = 0;
        int useLength = length;
        if (pause > 5) {
            if (!stop) {
                for (int yloop = 0; yloop < 2; yloop++) {
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
                        location = x + multiplier;
                        switch (this.getDirectionFacing()) {
                            case NORTH:
                                multiplier = -multiplier;
                                useLength = -length - 2;
                                location = x + multiplier;
                                break;
                            case EAST:
                                useLength = multiplier - 1;
                                location = (length + x) + 1;
                                break;
                            case WEST:
                                useLength = multiplier - 1;
                                location = ((x - length) - 1);
                                System.out.println(useLength + " | " + location);
                                break;
                        }
                        System.out.println(getDirectionFacing());

                        worldObj.setBlockToAir(location, yCoord + yloop, z + useLength + 1);
                    }

                }
            }
            if (length >= 10) {
                length = 0;
                stop = true;
            }
            length++;
            pause = 0;
        }
        pause++;
    }
}
