package com.przedtop.system.bankowy.controllers.model;



public class AccountRequestDataModel {

    private Long nrKonta;
    private double saldo;
    private String data_zalozenia;
    private int userId;

    @Override
    public String toString() {
        return "AccountRequestDataModel{" +
                "nrKonta=" + nrKonta +
                ", saldo=" + saldo +
                ", data_zalozenia='" + data_zalozenia + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Long getNrKonta() {
        return nrKonta;
    }

    public void setNrKonta(Long nrKonta) {
        this.nrKonta = nrKonta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getData_zalozenia() {
        return data_zalozenia;
    }

    public void setData_zalozenia(String data_zalozenia) {
        this.data_zalozenia = data_zalozenia;
    }
}
