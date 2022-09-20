package com.wzx.demo.common;

/**
 * API服务异常
 */
public class ApiException extends RuntimeException {

    private String code;

    private Object data;

    public ApiException(Response response) {
        this(response.getCode(), response.getMessage(), response.getData());
    }

    public ApiException(String code, String message) {
        this(code, message, null);
    }

    public ApiException(String code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public ApiException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

}
