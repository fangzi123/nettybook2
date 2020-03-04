package com.phei.netty.codec.messagepack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx)
                                            throws Exception {
        Student loopStudent;
        for (int i = 1; i <= 10; i++) {
            loopStudent = new Student();
            loopStudent.setName("冯冬冬"+i);
            loopStudent.setAge(i);
            ctx.write(loopStudent);
        }
        ctx.flush();
    }
}