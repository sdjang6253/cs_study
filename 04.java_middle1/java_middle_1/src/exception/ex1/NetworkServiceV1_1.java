package exception.ex1;


public class NetworkServiceV1_1 {
    public void sendMessage(String message) {
        String address = "http://example.com";
        NetworkClientV1 client = new NetworkClientV1(address);

        client.initError(message);

        client.connect();
        client.send(message);
        client.disconncet();

    }
}
