package com.phei.netty.codec.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

//编码器
public class MsgPackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          Object o, ByteBuf byteBuf) throws Exception {
        byte[] data = new MessagePack().write(o);
        byteBuf.writeBytes(data);
    }
}