package com.przedtop.bank.system.model;

public class PreHandleWebInceptorModel {
    private Long id;
    private String requestUrl;
    private String sender_ip;
    private String date;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getSender_ip() {
        return sender_ip;
    }

    public void setSender_ip(String sender_ip) {
        this.sender_ip = sender_ip;
    }

    public String getHandler() {
        return date;
    }

    public void setHandler(String handler) {
        this.date = handler;
    }
}
