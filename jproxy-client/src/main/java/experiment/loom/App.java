package experiment.loom;

public class App {
  public static void main(String[] args) {
    System.out.println("Starting up client");
    Client.sendRequest(args.length > 0 ? args[0] : null, args.length > 1 ? args[1] : null);
  }
}
