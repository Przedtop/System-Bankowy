package com.przedtop.bank.system.controllers.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;


public class AccountRequestDataModel {

    private Long id;
    @JsonAlias("accNo")
    @JsonProperty("accountNumber")
    private Long accountNumber;
    private double balance;
    @JsonAlias("cDate")
    @JsonProperty("createDate")
    private String createDate;
    @JsonAlias("uid")
    @JsonProperty("userId")
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

    public Long getId() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
