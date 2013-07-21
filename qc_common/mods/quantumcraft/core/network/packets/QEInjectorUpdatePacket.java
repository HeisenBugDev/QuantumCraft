package mods.quantumcraft.core.network.packets;

import mods.quantumcraft.core.network.abstractpackets.CoordinatesPacket;
import mods.quantumcraft.core.network.abstractpackets.ModernPacket;
import mods.quantumcraft.machine.TileQEInjector;
import net.minecraft.entity.player.EntityPlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class QEInjectorUpdatePacket extends CoordinatesPacket {


    public int heat;
    public int progress;

    public QEInjectorUpdatePacket(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new QEInjectorUpdatePacket(getID());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        TileQEInjector tile = getTile(player.worldObj, TileQEInjector.class);

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

