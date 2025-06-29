package exception.ex2;


public class NetworkServiceV2_2 {
    public void sendMessage(String message) {
        String address = "http://example.com";
        NetworkClientV2 client = new NetworkClientV2(address);

        client.initError(message);

        try {
            client.connect();
        } catch (NetworkClientExceptionV2 e) {
            System.out.println("[네트워크 오류 발생] 오류코드 = " + e.getErrorCode()  + ", 메세지: " + e.getMessage() );
            return;
        }
        try {
            client.send(message);
        } catch (NetworkClientExceptionV2 e) {
            System.out.println("[네트워크 오류 발생] 오류코드 = " + e.getErrorCode()  + ", 메세지: " + e.getMessage() );
            return;
        }
        client.disconncet();

    }
}
