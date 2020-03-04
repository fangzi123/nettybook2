package com.phei.netty.codec.messagepack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
      start();
    }
    public static void start() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            final ServerBootstrap b = new ServerBootstrap();
            b.group(boss, work)
             .channel(NioServerSocketChannel.class)
             .option(ChannelOption.SO_BACKLOG, 1024)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 protected void initChannel(SocketChannel ch) throws Exception {
                   ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                   ch.pipeline().addLast(new MsgPackDecoder());
                   ch.pipeline().addLast(new LengthFieldPrepender(2));
                   ch.pipeline().addLast(new MsgPackEncoder());
                   ch.pipeline().addLast(new ServerUAVHandler());
                 }
              });
            ChannelFuture f = b.bind(8883).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}