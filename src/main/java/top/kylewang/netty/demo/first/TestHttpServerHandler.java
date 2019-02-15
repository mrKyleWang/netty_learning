package top.kylewang.netty.demo.first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年02月14日
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

	private static final Logger logger = LoggerFactory.getLogger(TestHttpServer.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		if (msg instanceof HttpRequest) {
			HttpRequest httpRequest = (HttpRequest) msg;
			logger.debug("{}请求", httpRequest.method().name());
			ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
			FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
					content);
			httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
			ctx.writeAndFlush(httpResponse);
		}
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channel registered");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channel unregistered");

		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channel acitve");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channel inactive");
		super.channelInactive(ctx);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		logger.debug("handler added");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		logger.debug("handler removed");
		super.handlerRemoved(ctx);
	}
}
