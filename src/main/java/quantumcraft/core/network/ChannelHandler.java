package quantumcraft.core.network;

import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ChannelHandler extends FMLIndexedMessageToMessageCodec<IPacket> {

    public ChannelHandler() {
        addDiscriminator(0, MachinePacket.class);
    }

    @Override
    public void encodeInto(ChannelHandlerContext channelHandlerContext, IPacket iPacket, ByteBuf byteBuf){
        iPacket.writeBytes(byteBuf);
    }

    @Override
    public void decodeInto(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, IPacket iPacket) {
        iPacket.readBytes(byteBuf);
    }
}
