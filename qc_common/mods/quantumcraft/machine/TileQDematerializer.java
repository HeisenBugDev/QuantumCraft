package mods.quantumcraft.machine;

import mods.quantumcraft.machine.abstractmachines.TileEnergySink;
import net.minecraft.network.packet.Packet;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/24/13
 * Time: 8:48 PM
 */
public class TileQDematerializer extends TileEnergySink{
    @Override
    public int getMaxEnergy() {
        return 0;
    }

    @Override
    public int getCurrentEnergy() {
        return 0;
    }

    @Override
    public int subtractEnergy(int req) {
        return 0;
    }

    @Override
    public void onBlockBreak() {

    }

    @Override
    public void updateEntity() {

    }

    @Override
    public Packet getDescriptionPacket() {
        return null;
    }
}
