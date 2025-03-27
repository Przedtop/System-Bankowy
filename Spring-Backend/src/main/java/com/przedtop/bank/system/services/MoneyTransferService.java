package com.przedtop.bank.system.services;

import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.model.MoneyTransferDTO;
import com.przedtop.bank.system.repozytories.AccountsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferService {

    private final static Logger logger = LoggerFactory.getLogger(MoneyTransferService.class);

    private final AccountService accountService;
    private final AccountsRepo repo;

    public MoneyTransferService(AccountService accountService, AccountsRepo repo) {
        this.accountService = accountService;
        this.repo = repo;
    }

    public boolean moneyTransfer(MoneyTransferDTO moneyTransferDTO) {
        if (moneyTransferDTO.getSenderAccountNumber() != 0) {
            if (!moneyTransferDTO.getSenderAccountNumber().equals(moneyTransferDTO.getReceiverAccountNumber())) {
                if (accountService.getAccountByAccountNumber(moneyTransferDTO.getSenderAccountNumber()) != null &&
                        accountService.getAccountByAccountNumber(moneyTransferDTO.getReceiverAccountNumber()) != null) {

                    if(moneyTransferDTO.getAmountToTransfer() == 0) {
                        throw new IllegalArgumentException("Amount to transfer can't be equal to 0");
                    }

                    Accounts sender = accountService.getAccountByAccountNumber(moneyTransferDTO.getSenderAccountNumber());
                    Accounts receiver = accountService.getAccountByAccountNumber(moneyTransferDTO.getReceiverAccountNumber());

                    if (sender.getBalance() >= moneyTransferDTO.getAmountToTransfer()) {
                        sender.setBalance(sender.getBalance() - moneyTransferDTO.getAmountToTransfer());
                        receiver.setBalance(receiver.getBalance() + moneyTransferDTO.getAmountToTransfer());
                        repo.save(sender);
                        repo.save(receiver);
                        return true;
                    } else {
                        throw new IllegalArgumentException("Not enough money on sender account");
                    }
                } else {
                    throw new IllegalArgumentException("Account not found");
                }
            } else {
                throw new IllegalArgumentException("You can't transfer money to yourself");
            }
        } else {
            if (moneyTransferDTO.getAmountToTransfer() > 0 || moneyTransferDTO.getAmountToTransfer() < 0) {
                if (accountService.getAccountByAccountNumber(moneyTransferDTO.getReceiverAccountNumber()) != null) {
                    Accounts receiver = accountService.getAccountByAccountNumber(moneyTransferDTO.getReceiverAccountNumber());

                    receiver.setBalance(receiver.getBalance() + moneyTransferDTO.getAmountToTransfer());
                    repo.save(receiver);
                    return true;
                } else {
                    throw new IllegalArgumentException("Account not found");
                }
            } else{
                throw new IllegalArgumentException("Amount can't be equal to 0");
            }
        }
    }
}