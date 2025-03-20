package com.przedtop.bank.system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "connections")
public class PreHandleWebInceptor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int request_id;
    private String request_url;
    private String sender_ip;
    private String date;
    private String status;

    public String getSender_ip() {
        return sender_ip;
    }

    public void setSender_ip(String sender_ip) {
        this.sender_ip = sender_ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }
}
