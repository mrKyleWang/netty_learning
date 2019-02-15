package top.kylewang.netty.demo.second.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年02月15日
 */
public class TestSocketServerHandler extends SimpleChannelInboundHandler<String> {

	private static final Logger logger = LoggerFactory.getLogger(TestSocketServerHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		logger.info(ctx.channel().remoteAddress() + ":" + msg);
		ctx.writeAndFlush("from server:" + UUID.randomUUID());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
