package mods.quantumcraft.core.network.packets;

import mods.quantumcraft.util.BasicUtils;
import mods.quantumcraft.core.network.abstractpackets.CoordinatesPacket;
import mods.quantumcraft.core.network.abstractpackets.ModernPacket;
import mods.quantumcraft.machine.TileQDislocator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class QDislocatorInitPacket extends CoordinatesPacket {

    public NBTTagCompound tiledata;

    public QDislocatorInitPacket(int id) {
        super(id);
    }


    @Override
    public ModernPacket template() {
        return new QDislocatorInitPacket(getID());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        TileQDislocator tile = getTile(player.worldObj, TileQDislocator.class);
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