package learn.basic.hello.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient  {

    private String url ;
    private String hostname ;

    public NetworkClient() {
        System.out.println("생성자 호출 , url = " + url + " , hostname = " + hostname );
    }

    public void setUrl(String url) {
        System.out.println("NetworkClient.setUrl");
        this.url = url;
    }

    public void setHostname(String hostname) {
        System.out.println("NetworkClient.setHostname");
        this.hostname = hostname;
    }

    public void connect() {
        System.out.println("connect : "  + url + " , hostname = " + hostname);
    }

    public void call (String message) {
        System.out.println("call = " + url + " message = " + message + " , hostname = " + hostname);
    }

    public void disconnect() {
        System.out.println("close : " + url + " , hostname = " + hostname);
    }

    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close()  {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
