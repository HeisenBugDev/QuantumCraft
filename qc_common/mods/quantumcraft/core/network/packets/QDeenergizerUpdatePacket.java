package mods.quantumcraft.core.network.packets;

import mods.quantumcraft.core.network.abstractpackets.CoordinatesPacket;
import mods.quantumcraft.core.network.abstractpackets.ModernPacket;
import mods.quantumcraft.machine.TileQDeenergizer;
import net.minecraft.entity.player.EntityPlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class QDeenergizerUpdatePacket extends CoordinatesPacket {


    public int heat;
    public int progress;

    public QDeenergizerUpdatePacket(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new QDeenergizerUpdatePacket(getID());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        TileQDeenergizer tile = getTile(player.worldObj, TileQDeenergizer.class);

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
