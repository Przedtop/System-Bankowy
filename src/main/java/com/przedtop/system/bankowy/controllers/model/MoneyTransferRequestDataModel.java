package com.przedtop.system.bankowy.controllers.model;

public class MoneyTransferRequestDataModel {

    private Long nrKontaNadawcy;
    private Long nrKontaOdbiorcy;
    private double sumaDoPrzelania;

    public Long getNrKontaNadawcy() {
        return nrKontaNadawcy;
    }

    public void setNrKontaNadawcy(Long nrKontaNadawcy) {
        this.nrKontaNadawcy = nrKontaNadawcy;
    }

    public double getSumaDoPrzelania() {
        return sumaDoPrzelania;
    }

    public void setSumaDoPrzelania(double sumaDoPrzelania) {
        this.sumaDoPrzelania = sumaDoPrzelania;
    }

    public Long getNrKontaOdbiorcy() {
        return nrKontaOdbiorcy;
    }

    public void setNrKontaOdbiorcy(Long nrKontaOdbiorcy) {
        this.nrKontaOdbiorcy = nrKontaOdbiorcy;
    }

    @Override
    public String toString() {
        return "MoneyTransferRequestDataModel{" +
                "nrKontaNadawcy=" + nrKontaNadawcy +
                ", nrKontaOdbiorcy=" + nrKontaOdbiorcy +
                ", sumaDoPrzelania=" + sumaDoPrzelania +
                '}';
    }
}
