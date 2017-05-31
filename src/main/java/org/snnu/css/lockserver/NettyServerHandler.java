package org.snnu.css.lockserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class NettyServerHandler extends
		SimpleChannelInboundHandler<DatagramPacket> {


	private static final Map<String,InetSocketAddress> map = new HashMap<String, InetSocketAddress>();;

	/**
	 * channelRead0 是对每个发送过来的UDP包进行处理
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet)
			throws Exception {
		
		ByteBuf buf = (ByteBuf) packet.copy().content();
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		
		String data = new String(req, "UTF-8");
		System.out.println("data: "+data);
		if(data.startsWith("CMD:")){
			String mac = null;
			String cmd = null;
			mac = data.substring(4,21);
			cmd = data.substring(22);
		
			InetSocketAddress addr = map.get(mac);

			if(addr != null){
				System.out.println("addr: " + addr.toString());
				System.out.println("tmp: " + packet.sender());
				ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(cmd
						.getBytes()), addr));
			}

			
			System.out.println("发送指令到小主机......" + mac + " " + cmd);
			
		}else if(data.startsWith("MAC:")){
			
			String mac = data.substring(4);
			System.out.println("MAC: " + mac);
			InetSocketAddress addr = packet.sender();
			InetSocketAddress tmp = map.get(mac);
			if(tmp !=null){
				System.out.println("replace");
				map.replace(mac, addr);
			}else{
				System.out.println("put");
				map.put(mac, addr);
			}
			
		}else if(data.startsWith("KEEP:")){
			
		}

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("服务器启动...");
		super.channelActive(ctx);
	}
}
