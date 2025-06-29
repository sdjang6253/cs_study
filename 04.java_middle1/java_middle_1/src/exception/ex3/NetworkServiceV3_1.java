package exception.ex3;

import exception.ex3.exception.ConnectExceptionV3;
import exception.ex3.exception.SendExceptionV3;

public class NetworkServiceV3_1 {
    public void sendMessage(String message) {
        String address = "http://example.com";
        NetworkClientV3 client = new NetworkClientV3(address);

        client.initError(message);

        try {
            client.connect();
            client.send(message);
        } catch (ConnectExceptionV3 e) {
            System.out.println("[연결 오류] 주소 = " + e.getAddress()  + ", 메세지: " + e.getMessage() );
        } catch (SendExceptionV3 e) {
            System.out.println("[전송 오류] 전송데이터 = " + e.getSendData()  + ", 메세지: " + e.getMessage() );
        } finally {
            client.disconncet();
        }
    }
}
