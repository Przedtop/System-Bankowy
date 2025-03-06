package com.przedtop.system.bankowy.controllers.model;



public class AccountRequestDataModel {

    private String id;
    private Long nrKonta;
    private double saldo;
    private String dataUtworzenia;
    private int userId;


    @Override
    public String toString() {
        return "AccountRequestDataModel{" +
                "id='" + id + '\'' +
                ", nrKonta=" + nrKonta +
                ", saldo=" + saldo +
                ", dataUtworzenia='" + dataUtworzenia + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(String dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }
}
