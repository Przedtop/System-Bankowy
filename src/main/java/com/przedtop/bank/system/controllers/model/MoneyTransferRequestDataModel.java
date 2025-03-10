package com.przedtop.bank.system.controllers.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MoneyTransferRequestDataModel {

    @JsonAlias({"sender", "senderId", "from"})
    @JsonProperty("sender")
    @NotNull(message = "Sender account number is required")
    private Long senderAccountNumber;

    @JsonAlias({"receiver", "receiverId", "to"})
    @JsonProperty("receiver")
    @NotNull(message = "Receiver account number is required")
    private Long receiverAccountNumber;

    @JsonAlias({"value"})
    @JsonProperty("amount")
    @NotNull(message = "Transfer amount is required")
    @Min(value = 1, message = "Minimum transfer amount is 1")
    private Double amountToTransfer;

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

    public Long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public Double getAmountToTransfer() {
        return amountToTransfer;
    }
}