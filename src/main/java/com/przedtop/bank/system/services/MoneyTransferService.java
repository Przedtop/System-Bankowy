package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.AccountController;
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
        logger.trace("Starting money transfer");
        logger.trace("DATA {}", accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getReceiverAccountNumber()));
        logger.trace("DATA {}", accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getSenderAccountNumber()));
        if (accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getReceiverAccountNumber()) != null &&
                accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getSenderAccountNumber()) != null) {
            logger.trace("1st validation gate passed successfully");
            Accounts receiver = accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getReceiverAccountNumber());
            Accounts sender = accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getSenderAccountNumber());
            logger.trace("Sender: {} \nReceiver: {}", sender, receiver);
            double senderBalance = accountService.getBalanceByAccountNumber(sender.getAccountNumber());
            double receiverBalance = accountService.getBalanceByAccountNumber(receiver.getAccountNumber());
            if (sender.getAccountNumber() != 0) {
                if (senderBalance >= moneyTransferRequestDataModel.getAmountToTransfer()) {
                    receiver.setBalance(receiverBalance + moneyTransferRequestDataModel.getAmountToTransfer());
                    sender.setBalance(senderBalance - moneyTransferRequestDataModel.getAmountToTransfer());

                    repo.save(receiver);
                    repo.save(sender);

                    return true;
                } else {
                    logger.warn("Transfer failed insufficient funds");
                    return false;
                }
            } else {
                receiver.setBalance(receiverBalance + moneyTransferRequestDataModel.getAmountToTransfer());

                repo.save(receiver);
                return true;
            }
        } else {
            logger.warn("Receiver or sender not found");
        }
        logger.trace("Transfer failed");
        return false;
    }
}

