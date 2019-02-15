package top.kylewang.netty.demo.second.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import static top.kylewang.netty.demo.CommonConstant.*;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年02月15日
 */
public class TestSocketClient {

	public static void main(String[] args) {
		NioEventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(workGroup).channel(NioSocketChannel.class).handler(new TestSocketClientInitializer());
			ChannelFuture channelFuture = bootstrap.connect(ADDRESS, PORT).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workGroup.shutdownGracefully();
		}

	}
}
