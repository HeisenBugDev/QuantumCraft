package mods.quantumcraft.machine;

import mods.quantumcraft.core.network.PacketHandler;
import mods.quantumcraft.core.network.packets.QElectrifierInitPacket;
import mods.quantumcraft.machine.abstractmachines.TileEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/26/13
 * Time: 8:59 AM
 */
public class TileQElectrifier extends TileEnergySink {
    private int energyBuffer;

    @Override
    public int getMaxEnergy() {
        return 1000;
    }

    @Override
    public int getCurrentEnergy() {
        return 500;
    }

    @Override
    public int subtractEnergy(int req) {
        energyBuffer -= req;
        if (energyBuffer < 0) energyBuffer = 0;
        if (energyBuffer > getMaxEnergy()) energyBuffer = getMaxEnergy();
        return energyBuffer;
    }

    @Override
    public void onBlockBreak() {

    }

    @Override
    public void updateEntity() {

    }

    @Override
    public Packet getDescriptionPacket() {
        QElectrifierInitPacket packet = PacketHandler.getPacket(QElectrifierInitPacket.class);
        packet.posX = xCoord;
        packet.posY = yCoord;
        packet.posZ = zCoord;
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        packet.tiledata = nbt;

        return packet.getPacket();
    }
}
