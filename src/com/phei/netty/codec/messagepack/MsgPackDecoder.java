package com.phei.netty.codec.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

//解码器
public class MsgPackDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {

        byte[] array = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(array);
        Object obj =  new MessagePack().read(array);
        list.add(obj);
    }
}