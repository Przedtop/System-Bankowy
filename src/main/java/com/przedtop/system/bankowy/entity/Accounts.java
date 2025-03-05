package com.przedtop.system.bankowy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nr_konta")
    private long nrKonta;
    @Column(name="saldo")
    private double saldo;
    @Column(name = "data_zalozenia")
    private String data_utworzenia;
    @Column(name = "user_id")
    private long userId;


    @Override
    public String toString() {
        return "Accounts{" +
                "nrKonta=" + nrKonta +
                ", saldo=" + saldo +
                ", data_utworzenia='" + data_utworzenia + '\'' +
                ", userId=" + userId +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getNrKonta() {
        return nrKonta;
    }

    public void setNrKonta(long nrKonta) {
        this.nrKonta = nrKonta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getData_utworzenia() {
        return data_utworzenia;
    }

    public void setData_utworzenia(String data_utworzenia) {
        this.data_utworzenia = data_utworzenia;
    }
}
