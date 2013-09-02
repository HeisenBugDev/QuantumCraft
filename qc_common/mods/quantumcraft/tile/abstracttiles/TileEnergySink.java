package mods.quantumcraft.tile.abstracttiles;

import mods.quantumcraft.net.EnergySourceList;
import mods.quantumcraft.net.Location;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEnergySink extends TileMachineBase {

    private EnergySourceList sourceList;

    public void addSourceToList(Location l, Location source) {
        if (sourceList == null) sourceList = new EnergySourceList();
        sourceList.addSource(source);
    }

    public void replaceSourceList(Location l, EnergySourceList sources) {
        sourceList = sources;
        System.out.println("REPLACING SOURCE LIST");
    }

    /**
     * @return maximum energy this machine can hold
     */
    public abstract int getMaxEnergy();

    /**
     * @return current energy the machine is holding
     */
    public abstract int getCurrentEnergy();

    /**
     * Adds energy to current buffer. uses substractEnergy with negative nubers.
     * @param req amount to add
     * @return energy buffer _AFTER_ addition
     */
    public int addEnergy(int req) {
        return subtractEnergy(-req);
    }

    /**
     * Subtracts energy from current buffer. INCLUDE ZERO AND MAXCHECKING
     * @param req amount to subtract
     * @return energy buffer _AFTER_ subtraction
     */
    public abstract int subtractEnergy(int req);

    /**
     * Method to receive an energy packet from an energy source
     * @param size size of the requested packet
     * @return actual amount of energy received
     */
    public int requestPacket(int size) {
        if (sourceList != null) return sourceList.getQuantumEnergy(worldObj, size);
        System.out.println("NULL!!!!!!!!!!!!!!!!!!!!!!!!");
        return 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        sourceList = EnergySourceList.read(par1NBTTagCompound.getCompoundTag("ENET"));
    }

    /**
     * Writes a tile entity to NBT.
     */

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setCompoundTag("ENET", sourceList.write());
        super.writeToNBT(par1NBTTagCompound);
    }
}
