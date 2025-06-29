package enumeration.test.http;

public enum HttpStatus {
    OK(200, "OK" ) ,
    BAD_REQUEST(400, "Bad Reqeust" ),
    NOT_FOUND(404 , "Not Found" ) ,
    INTERNAL_SERVER_ERROR(500, "Internal Server Error" );

    private int code;
    private String message;

    HttpStatus(int code, String message ) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return code >= 200 && code < 300;
    }

    public static HttpStatus findByCode(int findCode){
        HttpStatus[] status = values();
        for(HttpStatus httpStatus : status){
            if(httpStatus.getCode() == findCode){
                return httpStatus;
            }
        }
        return null;
    }
}
