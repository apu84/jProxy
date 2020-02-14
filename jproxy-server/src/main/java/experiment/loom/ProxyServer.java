package experiment.loom;

import com.google.common.base.Strings;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ProxyServer {
  static final int PORT = 5454;
  static final int KEEP_ALIVE = 20000;

  public static void start(final String portArg) throws IOException {
    var port = Strings.isNullOrEmpty(portArg) ? PORT : Integer.parseInt(portArg);

    var selector = Selector.open();
    var serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.bind(new InetSocketAddress("localhost", port));
    serverSocketChannel.configureBlocking(false);
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
      selector.select();
      var selectedKeys = selector.selectedKeys();
      var selectionKeyIterator = selectedKeys.iterator();

      while (selectionKeyIterator.hasNext()) {
        var selectionKey = selectionKeyIterator.next();
        if (selectionKey.isAcceptable()) {
          var socketChannel = (SocketChannel) selectionKey.channel();
          socketChannel.configureBlocking(false);
          socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
          var socketChannel = (SocketChannel) selectionKey.channel();
          var data = ByteBuffer.allocate(1024);
          socketChannel.read(data);
          System.out.println(new String(data.array()));
        }
      }
    }
  }
}
