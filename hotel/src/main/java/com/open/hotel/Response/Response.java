package com.open.hotel.Response;

public class Response {
    private int code;
    private Object value;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Response code(int code){
        this.code = code;
        return this;
    }

    public Response msg(String msg){
        this.msg = msg;
        return this;
    }

    public Response value(Object value){
        this.value = value;
        return this;
    }
}
