package experiment.loom;

import com.google.common.base.Strings;

public class Client {
  static final String HOST = "localhost";
  static final int PORT = 5454;

  public static void sendRequest(final String pHost, final String pPort) {
    var host = Strings.isNullOrEmpty(pHost) ? HOST : pHost;
    var port = Strings.isNullOrEmpty(pPort) ? PORT : Integer.parseInt(pPort);
    System.out.println("Send request");
  }
}
