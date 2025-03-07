package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.MoneyTransferRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.repozytories.AccountsRepo;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferService {


    private final AccountService accountService;
    private final AccountsRepo repo;

    public MoneyTransferService(AccountService accountService, AccountsRepo repo) {
        this.accountService = accountService;
        this.repo = repo;
    }

    public boolean moneyTransfer(MoneyTransferRequestDataModel moneyTransferRequestDataModel) {
        if(accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getReceiverAccountNumber())!=null &&
                accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getSenderAccountNumber())!=null) {
            Accounts receiver = accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getReceiverAccountNumber());
            Accounts sender = accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getSenderAccountNumber());

            double senderBalance = accountService.getBalanceByAccountNumber(sender.getAccountNumber());
            double receiverBalance = accountService.getBalanceByAccountNumber(receiver.getAccountNumber());

            if (sender.getAccountNumber() != 0) {
                if (senderBalance >= moneyTransferRequestDataModel.getAmountToTransfer()) {
                    receiver.setBalance(receiverBalance + moneyTransferRequestDataModel.getAmountToTransfer());
                    sender.setBalance(senderBalance - moneyTransferRequestDataModel.getAmountToTransfer());

                    repo.save(receiver);
                    repo.save(sender);

                    return true;
                }
            } else {
                receiver.setBalance(receiverBalance + moneyTransferRequestDataModel.getAmountToTransfer());

                repo.save(receiver);
                return true;
            }
        }
        return false;
    }
}

