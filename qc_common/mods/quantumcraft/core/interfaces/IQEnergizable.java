package mods.quantumcraft.core.interfaces;

public interface IQEnergizable {

    /**
     *
     * @return maximum energy the item can hold
     */
    public int getMaxQEnergyValue();

    /**
     *
     * @return the current energy value the item's got
     */
    public int getCurrentQEnergyBuffer();

    /**
     *
     * @param value value to set the buffer to
     * @return value AFTER the change
     */
    public int setCurrentQEnergyBuffer(int value);

}
