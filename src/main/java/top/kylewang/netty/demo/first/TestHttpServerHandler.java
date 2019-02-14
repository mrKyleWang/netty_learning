package top.kylewang.netty.demo.first;

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

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		if (msg instanceof HttpRequest){
			ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
			FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
					content);
			httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
			ctx.writeAndFlush(httpResponse);
		}
	}
}
