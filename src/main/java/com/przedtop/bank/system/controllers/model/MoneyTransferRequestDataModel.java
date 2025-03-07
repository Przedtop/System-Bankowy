package com.przedtop.bank.system.controllers.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoneyTransferRequestDataModel {

    @JsonAlias({"sender", "senderId", "from"})
    @JsonProperty("sender")
    private Long senderAccountNumber;
    @JsonAlias({"receiver", "receiverId", "to"})
    @JsonProperty("receiver")
    private Long receiverAccountNumber;
    @JsonAlias({"value"})
    @JsonProperty("amount")
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
