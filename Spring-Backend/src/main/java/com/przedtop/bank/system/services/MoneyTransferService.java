package com.przedtop.bank.system.services;

import com.przedtop.bank.system.model.MoneyTransferDTO;
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

    public boolean moneyTransfer(MoneyTransferDTO moneyTransferDTO) {
        if (accountService.getAccountByAccountNumber(moneyTransferDTO.getReceiverAccountNumber()) != null &&
                accountService.getAccountByAccountNumber(moneyTransferDTO.getSenderAccountNumber()) != null) {
            Accounts receiver = accountService.getAccountByAccountNumber(moneyTransferDTO.getReceiverAccountNumber());
            Accounts sender = accountService.getAccountByAccountNumber(moneyTransferDTO.getSenderAccountNumber());
            double senderBalance = accountService.getBalanceByAccountNumber(sender.getAccountNumber());
            double receiverBalance = accountService.getBalanceByAccountNumber(receiver.getAccountNumber());

            if (sender.getAccountNumber() != 0) {
                if (moneyTransferDTO.getAmountToTransfer() > 0) {
                    if (senderBalance >= moneyTransferDTO.getAmountToTransfer()) {
                        receiver.setBalance(receiverBalance + moneyTransferDTO.getAmountToTransfer());
                        sender.setBalance(senderBalance - moneyTransferDTO.getAmountToTransfer());

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
            } else if(moneyTransferDTO.getAmountToTransfer() > 0) {
                    receiver.setBalance(receiverBalance + moneyTransferDTO.getAmountToTransfer());
                    repo.save(receiver);
                    logger.info("Deposited successfully");
                    return true;
            } else if(moneyTransferDTO.getAmountToTransfer() < 0){
                if(0 <= receiverBalance+ moneyTransferDTO.getAmountToTransfer()) {
                    receiver.setBalance(receiverBalance + moneyTransferDTO.getAmountToTransfer());
                    repo.save(receiver);
                    logger.info("Withdrawn successfully");
                    return true;
                } else {
                    logger.warn("Insufficient balance, cannot withdraw");
                    return false;
                }
            }
        }else {
            logger.warn("Account not found");
            return false;
        }
        return false;
    }
}