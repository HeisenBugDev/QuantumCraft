package quantumcraft.tile.abstracttiles;

import net.minecraft.nbt.NBTTagCompound;
import quantumcraft.net.Location;

public abstract class TileEnergySource extends TileMachineBase {
    private int energyBuffer = 0;
    /**
     * @return maximum energy this machine can hold
     */
    public abstract int getMaxEnergy();

    /**
     * @return current energy the machine is holding
     */
    public int getCurrentEnergy() {
        return energyBuffer;
    }

    /**
     * Adds energy to current buffer. uses substractEnergy with negative nubers.
     *
     * @param req amount to add
     * @return energy buffer _AFTER_ addition
     */
    public int addEnergy(int req) {
        return subtractEnergy(-req);
    }

    /**
     * Subtracts energy from current buffer. INCLUDE ZERO AND MAXCHECKING
     *
     * @param req amount to subtract
     * @return energy buffer _AFTER_ subtraction
     */
    public int subtractEnergy(int req) {
        energyBuffer -= req;
        if (energyBuffer < 0) energyBuffer = 0;
        if (energyBuffer > getMaxEnergy()) energyBuffer = getMaxEnergy();
        return energyBuffer;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.energyBuffer = par1NBTTagCompound.getInteger("energyBuffer");
        updateNextTick = true;
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("energyBuffer", this.energyBuffer);

        super.writeToNBT(par1NBTTagCompound);
    }

    public int getQuantumEnergy(Location l, int request) {
        if (getCurrentEnergy() >= request) {
            subtractEnergy(request);
            return request;
        } else {
            int e = getCurrentEnergy();
            subtractEnergy(request);
            return e;
        }
    }
}
