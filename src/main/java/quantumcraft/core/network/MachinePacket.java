package quantumcraft.core.network;

import io.netty.buffer.ByteBuf;

public class MachinePacket implements IPacket {

    int i;

    public MachinePacket() {
    }

    public MachinePacket(int i) {
        this.i = 1;
    }

    @Override
    public void readBytes(ByteBuf bytes) {
        i = bytes.readInt();

        System.out.println("Recieved packet with the int i = " + i);
    }

    @Override
    public void writeBytes(ByteBuf bytes) {
        bytes.writeInt(i);
    }
}
