package mods.quantumcraft.core.network.packets;

import mods.quantumcraft.core.BasicUtils;
import mods.quantumcraft.core.network.abstractpackets.CoordinatesPacket;
import mods.quantumcraft.core.network.abstractpackets.ModernPacket;
import mods.quantumcraft.machine.TileQEInjector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class QEInjectorInitPacket extends CoordinatesPacket {

    public NBTTagCompound tiledata;

    public QEInjectorInitPacket(int id) {
        super(id);
    }


    @Override
    public ModernPacket template() {
        return new QEInjectorInitPacket(getID());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        TileQEInjector tile = getTile(player.worldObj, TileQEInjector.class);
        if (tile != null) {
            tile.readFromNBT(tiledata);
            tile.updateNextTick = true;
        }
    }

    @Override
    public void writeData(DataOutputStream data) throws IOException {
        super.writeData(data);
        BasicUtils.writeNBTToData(tiledata, data);
    }

    @Override
    public void readData(DataInputStream data) throws IOException {
        super.readData(data);
        tiledata = (NBTTagCompound) BasicUtils.readNBTFromData(data);
    }
}