package top.kylewang.netty.demo.first;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年02月14日
 */
public class TestHttpServerInitializer extends ChannelInitializer<SocketChannel> {

	private static final Logger logger = LoggerFactory.getLogger(TestHttpServerInitializer.class);

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("httpServerCodec", new HttpServerCodec());
		pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
		logger.debug("channel init end");
	}
}
