package top.kylewang.netty.demo.other;

import top.kylewang.netty.demo.CommonConstant;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestSocketDemo {

	public static void main(String[] args) {
		new Thread(new NioTestServer()).start();

	}

	static class NioTestServer implements Runnable {

		@Override
		public void run() {
			try {
				ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
				serverSocketChannel.socket().bind(new InetSocketAddress(CommonConstant.PORT));
				serverSocketChannel.configureBlocking(false);
				int count = 0;
				while (true) {
					ByteBuffer buf = ByteBuffer.allocate(128);
					buf.clear();
					// 此处accept()无连接时会返回null，而不是阻塞
					SocketChannel socketChannel = serverSocketChannel.accept();
					while (socketChannel != null && socketChannel.read(buf) > 0) {
						buf.flip();
						while (buf.hasRemaining()) {
							System.out.print((char) buf.get());
						}
						buf.compact();
					}
					Thread.sleep(1000);
					System.out.println(++count);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
