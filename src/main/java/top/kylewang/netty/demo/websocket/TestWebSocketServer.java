package top.kylewang.netty.demo.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.kylewang.netty.demo.first.TestHttpServer;

import java.text.SimpleDateFormat;
import java.util.Date;

import static top.kylewang.netty.demo.CommonConstant.PORT;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年02月15日
 */
public class TestWebSocketServer {

	private static final Logger logger = LoggerFactory.getLogger(TestHttpServer.class);

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static void main(String[] args) {
		groupSend();
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new TestWebSocketServerInitializer());
			ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
			logger.debug("server start,port = {}", PORT);
			channelFuture.channel().closeFuture().sync();
			logger.debug("server close");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			logger.debug("server shutdown");
		}
	}

	public static void groupSend() {
		new Thread() {
			@Override
			public void run() {
				for (;;) {
					String time = dateFormat.format(new Date());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (Channel channel : TestWebSocketChannelGroup.getAllChannel()) {
						if (channel.isActive()) {
							channel.writeAndFlush(new TextWebSocketFrame("服务器时间: " + time));
						}
					}
				}
			}
		}.start();
	}
}
