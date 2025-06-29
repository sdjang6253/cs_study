package exception.ex2;


public class NetworkServiceV2_1 {
    public void sendMessage(String message) throws NetworkClientExceptionV2 {
        String address = "http://example.com";
        NetworkClientV2 client = new NetworkClientV2(address);

        client.initError(message);

        client.connect();
        client.send(message);
        client.disconncet();

    }
}
