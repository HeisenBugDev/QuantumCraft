package quantumcraft.core.network.packets;

import quantumcraft.util.BasicUtils;
import quantumcraft.core.network.abstractpackets.CoordinatesPacket;
import quantumcraft.core.network.abstractpackets.ModernPacket;
import quantumcraft.tile.TileQEExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class QEExtractorInitPacket extends CoordinatesPacket {


    public NBTTagCompound tiledata;

    public QEExtractorInitPacket(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new QEExtractorInitPacket(getID());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        TileQEExtractor tile = getTile(player.worldObj, TileQEExtractor.class);
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
