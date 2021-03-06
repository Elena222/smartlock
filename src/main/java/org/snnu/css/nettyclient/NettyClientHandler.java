package org.snnu.css.nettyclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;

//L
public class NettyClientHandler extends SimpleChannelInboundHandler<DatagramPacket>{
	
	private String cmd;
	
	public NettyClientHandler(String cmd){
		this.cmd = cmd;
	}
	
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet)
            throws Exception {
        //服务器推送对方IP和PORT
        ByteBuf buf = (ByteBuf) packet.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String str = new String(req, "UTF-8");
        System.out.println("接收到的信息:" + str);
        
        
        
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("启动.......");
       ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("10.10.10.173"
				.getBytes()), new InetSocketAddress("10.10.10.100", 8618)));
        super.channelActive(ctx);
    }
}
