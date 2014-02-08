package quantumcraft.tile;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.ForgeDirection;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import quantumcraft.util.Coords;
import quantumcraft.util.IONTunnelerGlobalRemovalList;

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
    private boolean done = false;
    private List<Coords> blockRemovalQueue = new ArrayList<Coords>();

    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int guiID() {
        return -1;
    }

    @Override
    public void onBlockBreak() {

    }


    /**
     * This will calculate the value of the length integer. It does so by finding the direction of the block and then
     * doing a 'lil math based off of the shifter and length.
     *
     * @return The value of length
     */
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
        return tmpValue + z + 1;
    }

    /**
     * The shifter is the value of how many spaces to go before getting to the next path (3 block in middle = 4 blocks)
     *
     * @return shifter
     */
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

    /**
     * @return X value
     */
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

    @Override
    public String getStatusText() {
        if (done) return EnumChatFormatting.GREEN + "Done";
        if (this.getCurrentEnergy() < 1 && stop) return EnumChatFormatting.RED + "Waiting for power!";
        if (this.getCurrentEnergy() < 1) return EnumChatFormatting.RED + "Out of Power!";
        return EnumChatFormatting.GREEN + "" + blockRemovalQueue.size() + " blocks left";
    }

    /**
     * The amazing math of how it calculates what blocks to flag to remove. More info it individual comments.
     */
    public void dig() {
        // Verifies that it's not stopped yet
        if (!stop) {
            // Gets custom x, y, and z values
            y = yCoord;
            x = xCoord;
            z = zCoord;
            // First loop that is used for the y value
            for (int yloop = 0; yloop < 2; yloop++) {
                // Modifier loop that will change the shifter
                for (int mloop = 1; mloop < 5; mloop++) {
                    // Shifter loop.
                    for (int i = 0; i < 3; i++) {
                        // Shifter calculator
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
                        // Get shifter based off of mloop and shifter itself
                        shifter = shifter * mloop;
                        // Basic location shift
                        location = x + shifter;
                        // Get shifter based off of orientation
                        shifter = getShifter();
                        // get length (z) based off of orientation
                        useLength = getUseLength();
                        // get location (x) based off of orientation
                        location = getLocation();

                        // Checks to see if it is either the first or last line
                        if (length == 0 || length >= 50) {
                            // Loops all blocks between each path
                            for (int q = 1; q < 4; q++) {
                                // Get location for NORTH/SOUTH
                                int locationT = getLocation() + q;
                                // Get length for NORTH/SOUTH
                                int useLengthT = useLength;
                                // Is it EAST/WEST?
                                if (this.getDirectionFacing() == ForgeDirection.EAST ||
                                        this.getDirectionFacing() == ForgeDirection.WEST) {
                                    //Change location and length if it is
                                    locationT = locationT - q;
                                    useLengthT = useLengthT + q;
                                }
                                // Check to see if is NOT air
                                if (worldObj.getBlockId(locationT, yCoord + yloop, useLengthT) != 0) {
                                    // Flag block for removal
                                    addBlockRemoval(new Coords(locationT, yCoord + yloop, useLengthT));
                                }
                            }
                        }
                        // Check to see if it is NOT air
                        if (worldObj.getBlockId(location, yCoord + yloop, useLength) != 0) {
                            // Flag block for removal
                            addBlockRemoval(new Coords(location, yCoord + yloop, useLength));
                        }
                    }
                }
            }
            // Resets length and stops it
            if (length >= 50) {
                length = 0;
                stop = true;
            }
            // Increment length
            length++;
        }

    }

    /**
     * Goes through the list of blocks needing removal and removes them every 5 ticks.
     */
    public void processBlockRemoval() {
        if (pause >= 5) {
            if (blockRemovalQueue.size() > 0) {
                if (blockRemovalQueue.get(0) != null && getCurrentEnergy() > 0) {
                    subtractEnergy(1);
                    int x = blockRemovalQueue.get(0).x;
                    int y = blockRemovalQueue.get(0).y;
                    int z = blockRemovalQueue.get(0).z;
                    worldObj.setBlockToAir(x, y, z);
                    IONTunnelerGlobalRemovalList.blocks.remove(blockRemovalQueue.get(0));
                    blockRemovalQueue.remove(0);
                    pause = 0;
                }
            } else if (stop) {
                done = true;
            }
        }
        pause++;
    }

    /**
     * Adds a block to the removal queue
     *
     * @param coord Coords object that contains where that block is.
     */
    public void addBlockRemoval(Coords coord) {
        IONTunnelerGlobalRemovalList.blocks.add(coord);
        blockRemovalQueue.add(coord);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (redstonePower) return;
        if (this.getCurrentEnergy() < this.getMaxEnergy() && blockRemovalQueue.size() > this.getCurrentEnergy()) {
            this.addEnergy(this.requestPacket(100));
        }
        processBlockRemoval();
        dig();
    }
}
