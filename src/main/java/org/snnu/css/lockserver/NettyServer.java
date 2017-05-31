package org.snnu.css.lockserver;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class NettyServer extends Thread {

    private Bootstrap b= null;
    private EventLoopGroup group = null;

    private void initServer() {
        System.out.println("in+++++++++");
        b = new Bootstrap();
        group = new NioEventLoopGroup();
        try {
            b.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new NettyServerHandler());

            b.bind("10.10.10.173", 8618).sync().channel().closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
        System.out.println("out++++++++");
    }


    @Override
    public void run() {
        initServer();
    }

    public void stopServer(){
        if(group!=null){
            group.shutdownGracefully();
            group = null;
        }

    }

}
