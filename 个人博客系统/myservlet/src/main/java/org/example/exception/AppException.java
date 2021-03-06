package org.example.exception;

public class AppException extends RuntimeException {

    private String code;//保存抛异常时给定的错误码

    public AppException(String code,String message) {
        /*super(message);
        this.code = code;*/
        this(code,message,null);
    }

    public AppException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
