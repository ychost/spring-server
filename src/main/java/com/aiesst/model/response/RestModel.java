package com.aiesst.model.response;

/**
 * Created by ychost on 17-6-28.
 * 与前端交互的基础rest模型
 */
public class RestModel {
    private int code;
    private String message;
    private Object data;

    public  RestModel(){
        this.code = RestCode.Ok;
        this.message="操作成功";
    }

    public int getCode() {
        return code;
    }

    public RestModel setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RestModel setData(Object data) {
        this.data = data;
        return this;
    }
}
