package experiment.loom;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    System.out.println("Spinning up Proxy server");
    try {
      ProxyServer.start(args.length > 0 ? args[0]: null);
    } catch (IOException ie) {
      ie.printStackTrace();
    }
  }
}
