package quantumcraft.tile;

import net.minecraftforge.common.ForgeDirection;
import quantumcraft.core.Coords;
import quantumcraft.tile.abstracttiles.TileEnergySink;

import java.util.ArrayList;
import java.util.List;

public class TileIONTunneler extends TileEnergySink {
    int length = 0;
    int shifter = 0;
    boolean stop = false;
    int pause = 0;
    int y = yCoord;
    int x = xCoord;
    int z = zCoord;
    int location = 0;
    int useLength = length;
    private List<Coords> blockRemovalQueue = new ArrayList<Coords>();

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

    public int getUseLength() {
        int tmpValue = 0;
        switch (this.getDirectionFacing()) {
            case NORTH:
                tmpValue = -length - 2;
                break;
            case EAST:
                tmpValue = shifter - 1;
                break;
            case WEST:
                tmpValue = shifter - 1;
                break;
            case SOUTH:
                tmpValue = length;
                break;
        }
        return tmpValue;
    }

    public int getShifter() {
        int tmpValue = 0;
        switch (this.getDirectionFacing()) {
            case NORTH:
                tmpValue = -shifter;
                break;
            case EAST:
                tmpValue = shifter;
                break;
            case WEST:
                tmpValue = shifter;
                break;
            case SOUTH:
                tmpValue = shifter;
                break;
        }
        return tmpValue;
    }

    public int getLocation() {
        int tmpValue = 0;
        switch (this.getDirectionFacing()) {
            case NORTH:
                tmpValue = x + shifter;
                break;
            case EAST:
                tmpValue = (length + x) + 1;
                break;
            case WEST:
                tmpValue = ((x - length) - 1);
                break;
            case SOUTH:
                tmpValue = location;
                break;
        }
        return tmpValue;
    }

    public void dig() {
        if (!stop) {
            y = yCoord;
            x = xCoord;
            z = zCoord;
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
                        shifter = getShifter();
                        useLength = getUseLength();
                        location = getLocation();
                        if (length == 0 || length >= 50) {
                            for (int q = 1; q < 4; q++) {
                                int locationT = getLocation() + q;
                                int useLengthT = useLength;
                                if (this.getDirectionFacing() == ForgeDirection.EAST ||
                                        this.getDirectionFacing() == ForgeDirection.WEST) {
                                    locationT = locationT - q;
                                    useLengthT = useLengthT + q;
                                }
                                addBlockRemoval(new Coords(locationT, yCoord + yloop, z + useLengthT + 1));
                            }
                        }
                        addBlockRemoval(new Coords(location, yCoord + yloop, z + useLength + 1));
                    }
                }
            }
            if (length >= 50) {
                length = 0;
                stop = true;
            }
            length++;
        }

    }

    public void processBlockRemoval() {
        if (pause >= 5) {
            if (blockRemovalQueue.get(0) != null && getCurrentEnergy() > 0) {
                subtractEnergy(1);
                int x = blockRemovalQueue.get(0).x;
                int y = blockRemovalQueue.get(0).y;
                int z = blockRemovalQueue.get(0).z;
                worldObj.setBlockToAir(x, y, z);
                blockRemovalQueue.remove(0);
                pause = 0;
            }
        }
        pause++;
    }

    public void addBlockRemoval(Coords coord) {
        blockRemovalQueue.add(coord);
    }

    @Override
    public void updateEntity() {
        if (this.getCurrentEnergy() < this.getMaxEnergy()) {
            this.addEnergy(this.requestPacket(100));
        }
        processBlockRemoval();
        dig();
    }
}
