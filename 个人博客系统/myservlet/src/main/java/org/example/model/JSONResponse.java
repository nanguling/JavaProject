package org.example.model;

/**
 * 前后端约定的统一数据格式 : 返回给前端的状态码都是200
 */
public class JSONResponse {
    //业务操作是否成功
    private boolean success;
    //错误码：开发人员使用来定位问题
    private String code;
    //错误信息：给用户看的信息
    private String message;
    //业务数据
    private Object data;



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JSONResponse{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
