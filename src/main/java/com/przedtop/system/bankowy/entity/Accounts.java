package com.przedtop.system.bankowy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nr_konta")
    private long nrKonta;
    @Column(name="saldo")
    private double saldo;
    @Column(name = "data_utworzenia")
    private String dataUtworzenia;
    @Column(name = "user_id")
    private long userId;

    @Override
    public String toString() {
        return "Accounts{" +
                "nrKonta=" + nrKonta +
                ", saldo=" + saldo +
                ", dataUtworzenia='" + dataUtworzenia + '\'' +
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

    public String getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(String data_utworzenia) {
        this.dataUtworzenia = data_utworzenia;
    }
}
