package com.lanou.utils;

/**
 * Created by yugege on 17/10/10.
 */
public class AjaxResult {

    private String message;
    private Integer errorCode;
    private Object data;

    public AjaxResult(Object data) {
        this.message = "添加成功";
        this.errorCode = 0;
        this.data = data;
    }

    public AjaxResult() {

    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", data=" + data +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
