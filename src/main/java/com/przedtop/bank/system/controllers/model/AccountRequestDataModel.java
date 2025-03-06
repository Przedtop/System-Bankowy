package com.przedtop.bank.system.controllers.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequestDataModel {

    private String id;
    @JsonProperty("accNo")
    private Long accountNumber;
    private double balance;
    @JsonProperty("cDate")
    private String createDate;
    @JsonProperty("uid")
    private int userId;

    @Override
    public String toString() {
        return "AccountRequestDataModel{" +
                "id='" + id + '\'' +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", createDate='" + createDate + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getId() {
        return id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public int getUserId() {
        return userId;
    }

    public String getCreateDate() {
        return createDate;
    }
}
