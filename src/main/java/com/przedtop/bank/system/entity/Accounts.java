package com.przedtop.bank.system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "accountNumber")
    private long accountNumber;
    @Column(name = "balance")
    private double balance;
    @Column(name = "createDate")
    private String createDate;
    @Column(name = "userId")
    private long userId;

    @Override
    public String toString() {
        return "Accounts{" +
                "nrKonta=" + accountNumber +
                ", balance=" + balance +
                ", createDate='" + createDate + '\'' +
                ", userId=" + userId +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long nrKonta) {
        this.accountNumber = nrKonta;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String data_utworzenia) {
        this.createDate = data_utworzenia;
    }
}
