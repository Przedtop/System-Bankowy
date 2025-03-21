package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.MoneyTransferRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
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

    public boolean moneyTransfer(MoneyTransferRequestDataModel moneyTransferRequestDataModel) {
        if (accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getReceiverAccountNumber()) != null &&
                accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getSenderAccountNumber()) != null) {
            Accounts receiver = accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getReceiverAccountNumber());
            Accounts sender = accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getSenderAccountNumber());
            double senderBalance = accountService.getBalanceByAccountNumber(sender.getAccountNumber());
            double receiverBalance = accountService.getBalanceByAccountNumber(receiver.getAccountNumber());

            if (sender.getAccountNumber() != 0) {
                if (moneyTransferRequestDataModel.getAmountToTransfer() > 0) {
                    if (senderBalance >= moneyTransferRequestDataModel.getAmountToTransfer()) {
                        receiver.setBalance(receiverBalance + moneyTransferRequestDataModel.getAmountToTransfer());
                        sender.setBalance(senderBalance - moneyTransferRequestDataModel.getAmountToTransfer());

                        repo.save(receiver);
                        repo.save(sender);

                        return true;
                    } else {
                        logger.warn("Insufficient balance, cannot transfer");
                        return false;
                    }

                } else {
                    logger.warn("Amount to transfer is below zero");
                    return false;
                }
            } else if(moneyTransferRequestDataModel.getAmountToTransfer() > 0) {
                    receiver.setBalance(receiverBalance + moneyTransferRequestDataModel.getAmountToTransfer());
                    repo.save(receiver);
                    logger.info("Deposited successfully");
                    return true;
            } else if(moneyTransferRequestDataModel.getAmountToTransfer() < 0){
                if(0 <= receiverBalance+moneyTransferRequestDataModel.getAmountToTransfer()) {
                    receiver.setBalance(receiverBalance + moneyTransferRequestDataModel.getAmountToTransfer());
                    repo.save(receiver);
                    logger.info("Withdrawn successfully");
                    return true;
                } else {
                    logger.warn("Insufficient balance, cannot withdraw");
                    return false;
                }
            }
        }
        logger.trace("Transfer failed");
        return false;
    }
}

