package exception.ex2;


public class NetworkServiceV2_3 {
    public void sendMessage(String message) {
        String address = "http://example.com";
        NetworkClientV2 client = new NetworkClientV2(address);

        client.initError(message);

        try {
            client.connect();
            client.send(message);
            client.disconncet();
        } catch (NetworkClientExceptionV2 e) {
            System.out.println("[네트워크 오류 발생] 오류코드 = " + e.getErrorCode()  + ", 메세지: " + e.getMessage() );
        }

    }
}
