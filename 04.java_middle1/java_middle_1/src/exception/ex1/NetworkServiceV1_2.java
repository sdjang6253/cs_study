package exception.ex1;


public class NetworkServiceV1_2 {
    public void sendMessage(String message) {
        String address = "http://example.com";
        NetworkClientV1 client = new NetworkClientV1(address);

        client.initError(message);


        String connectResult = client.connect();
        if(isError(connectResult)) {
            System.out.println("[네트워크 오류 발생] 오류코드 = " +connectResult );
            return;
        }

        String sendResult = client.send(message);
        if(isError(sendResult)) {
            System.out.println("[네트워크 오류 발생] 오류코드 = " +sendResult );
            return;
        }

        client.disconncet();

    }

    private static boolean isError(String connectResult) {
        return !connectResult.equals("success");
    }
}
