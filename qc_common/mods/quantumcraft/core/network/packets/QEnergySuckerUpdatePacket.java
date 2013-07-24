package mods.quantumcraft.core.network.packets;

import mods.quantumcraft.core.network.abstractpackets.CoordinatesPacket;
import mods.quantumcraft.core.network.abstractpackets.ModernPacket;
import mods.quantumcraft.machine.TileQEnergySucker;
import net.minecraft.entity.player.EntityPlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class QEnergySuckerUpdatePacket extends CoordinatesPacket {


    public int heat;
    public int progress;

    public QEnergySuckerUpdatePacket(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new QEInjectorUpdatePacket(getID());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        TileQEnergySucker tile = getTile(player.worldObj, TileQEnergySucker.class);

    }

    @Override
    public void writeData(DataOutputStream data) throws IOException {
        super.writeData(data);
        data.writeInt(heat);
        data.writeInt(progress);
    }

    @Override
    public void readData(DataInputStream data) throws IOException {
        super.readData(data);
        heat = data.readInt();
        progress = data.readInt();
    }
}

