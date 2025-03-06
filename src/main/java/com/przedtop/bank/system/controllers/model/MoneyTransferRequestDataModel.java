package com.przedtop.bank.system.controllers.model;

public class MoneyTransferRequestDataModel {

    private Long senderAccountNumber;
    private Long receiverAccountNumber;
    private double amountToTransfer;

    @Override
    public String toString() {
        return "MoneyTransferRequestDataModel{" +
                "senderAccountNumber=" + senderAccountNumber +
                ", receiverAccountNumber=" + receiverAccountNumber +
                ", amountToTransfer=" + amountToTransfer +
                '}';
    }

    public Long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public double getAmountToTransfer() {
        return amountToTransfer;
    }

    public Long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }


}
