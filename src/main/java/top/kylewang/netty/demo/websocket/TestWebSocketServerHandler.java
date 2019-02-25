package top.kylewang.netty.demo.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年02月15日
 */
public class TestWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	private static final Logger logger = LoggerFactory.getLogger(TestWebSocketServerHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		System.out.println(ctx.channel().id());
		TestWebSocketChannelGroup.addChannel(ctx.channel());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		System.out.println(ctx.channel().id());
		TestWebSocketChannelGroup.removeChannel(ctx.channel());
	}
}
