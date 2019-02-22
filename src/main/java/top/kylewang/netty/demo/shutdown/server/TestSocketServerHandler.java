package top.kylewang.netty.demo.shutdown.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
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

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		super.userEventTriggered(ctx, evt);
		if (evt instanceof IdleStateEvent) {
			// 主动关闭上级Channel，主线程会从停止阻塞，向下执行
			ctx.channel().parent().close();
		}
	}
}
