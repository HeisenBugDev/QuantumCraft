package quantumcraft.core.network.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import quantumcraft.core.network.abstractpackets.CoordinatesPacket;
import quantumcraft.core.network.abstractpackets.ModernPacket;
import quantumcraft.tile.abstracttiles.TileMachineBase;
import quantumcraft.util.BasicUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MachineInitPacket extends CoordinatesPacket {


    public NBTTagCompound tiledata;

    public MachineInitPacket(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new MachineInitPacket(getID());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        TileMachineBase tile = getTile(player.worldObj, TileMachineBase.class);
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
