package quantumcraft.tile;

import quantumcraft.core.network.PacketHandler;
import quantumcraft.core.network.packets.QEnergySuckerInitPacket;
import quantumcraft.tile.abstracttiles.TileEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

public class TileQEnergySucker extends TileEnergySink {
    @Override
    public int getMaxEnergy() {
        return 10;
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
    public int guiID() {
        return -1;
    }

    @Override
    public void onBlockBreak() {

    }

    @Override
    public void updateEntity() {
        this.requestPacket(10);
    }

    @Override
    public Packet getDescriptionPacket() {
        QEnergySuckerInitPacket packet = PacketHandler.getPacket(QEnergySuckerInitPacket.class);
        packet.posX = xCoord;
        packet.posY = yCoord;
        packet.posZ = zCoord;
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        packet.tiledata = nbt;

        return packet.getPacket();
    }
}
