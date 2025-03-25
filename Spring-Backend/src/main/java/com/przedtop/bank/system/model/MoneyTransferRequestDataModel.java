package com.przedtop.bank.system.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class MoneyTransferRequestDataModel {
    @JsonAlias({"sender", "senderId", "from"})
    @JsonProperty("senderAccountNumber")
    @NotNull(message = "Sender account number is required.")
    private Long senderAccountNumber;
    @JsonAlias({"receiver", "receiverId", "to"})
    @JsonProperty("receiverAccountNumber")
    @NotNull(message = "Receiver account number is required.")
    private Long receiverAccountNumber;
    @JsonAlias({"value", "amount"})
    @JsonProperty("amountToTransfer")
    @NotNull(message = "Amount to transfer is required.")
    private Double amountToTransfer;


    public String properUsage() {
        char separator = '"';
        return "Proper usage: " +
                "\n" + separator + "senderAccountNumber" + separator + ": value," +
                "\n" + separator + "receiverAccountNumber" + separator + ": value," +
                "\n" + separator + "amountToTransfer" + separator + ": value";
    }

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

    public Double getAmountToTransfer() {
        return amountToTransfer;
    }

    public Long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setSenderAccountNumber(Long senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public void setReceiverAccountNumber(Long receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public void setAmountToTransfer(double amountToTransfer) {
        this.amountToTransfer = amountToTransfer;
    }
}
