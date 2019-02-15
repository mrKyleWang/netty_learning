package top.kylewang.netty.demo.first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * TestServer
 * @author KyleWang
 * @version 1.0
 * @date 2019年02月14日
 */
public class TestServer {

	private static final Logger logger = LoggerFactory.getLogger(TestServer.class);

	private static final int PORT = 8888;

	public static void main(String[] args) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new TestServerInitializer());
			ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
			logger.debug("server start,port = {}", PORT);
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			logger.debug("server shutdown");
		}
	}
}
