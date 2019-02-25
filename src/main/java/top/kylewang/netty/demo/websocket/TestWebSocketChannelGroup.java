package top.kylewang.netty.demo.websocket;

import io.netty.channel.Channel;

import java.util.LinkedList;
import java.util.List;

public class TestWebSocketChannelGroup {

	private static List<Channel> group = new LinkedList<>();

	public static void addChannel(Channel channel) {
		group.add(channel);
	}

	public static void removeChannel(Channel channel) {
		group.remove(channel);
	}

	public static List<Channel> getAllChannel() {
		return group;
	}

}
