package top.kylewang.netty.demo.shutdown.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年02月15日
 */
public class TestSocketClientHandler extends SimpleChannelInboundHandler<String> {

	private static final Logger logger = LoggerFactory.getLogger(TestSocketClientHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		logger.info(ctx.channel().remoteAddress() + ":" + msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("来自客户端的问候~");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}
}
