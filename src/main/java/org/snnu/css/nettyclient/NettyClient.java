package org.snnu.css.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * 模拟P2P客户端
 * @author
 *
 */
public class NettyClient extends Thread{
	
	
	private String IP;
	private int Port;
	private String cmd;
	
	public NettyClient(String cmd, String IP, int Port){
		this.cmd = cmd;
		this.IP = IP;
		this.Port = Port;
	}
	

    private void init() {
    	 Bootstrap b = new Bootstrap();
         EventLoopGroup group = new NioEventLoopGroup();
         try {
             b.group(group)
                     .channel(NioDatagramChannel.class)
                     .option(ChannelOption.SO_BROADCAST, true)
                     .handler(new NettyClientHandler(cmd));


             b.connect(IP,Port).sync().channel().writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(cmd
     				.getBytes()), new InetSocketAddress("10.10.10.173", 8618)));
//             b.connect(IP,Port).sync().channel().writeAndFlush(cmd);
             //b.bind(Port).sync().channel().closeFuture().await();
         } catch (Exception e) {
             e.printStackTrace();
         } finally{
             group.shutdownGracefully();
         }
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
	}

}
