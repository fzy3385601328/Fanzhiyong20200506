package com.example.fanzhiyong20200506.bean;

public class Data<T> {
    private T result;
    private T orderList;
    private String message;
    private String status;

    public Data(String message) {
        this.message = message;
    }

    public Data(){

    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public T getOrderList() {
        return orderList;
    }

    public void setOrderList(T orderList) {
        this.orderList = orderList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
