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
        Accounts odbiorca = accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getReceiverAccountNumber());
        Accounts nadawca = accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getSenderAccountNumber());

        double saldoNadawcy = accountService.getBalanceByAccountNumber(nadawca.getAccountNumber());
        double saldoOdbiorcy = accountService.getBalanceByAccountNumber(odbiorca.getAccountNumber());

        if(nadawca.getAccountNumber()!=0) {
            if (saldoNadawcy >= moneyTransferRequestDataModel.getAmountToTransfer()) {
                odbiorca.setBalance(saldoOdbiorcy + moneyTransferRequestDataModel.getAmountToTransfer());
                nadawca.setBalance(saldoNadawcy - moneyTransferRequestDataModel.getAmountToTransfer());

                repo.save(odbiorca);
                repo.save(nadawca);

                return true;
            }
        }
        else{
            odbiorca.setBalance(saldoOdbiorcy + moneyTransferRequestDataModel.getAmountToTransfer());

            repo.save(odbiorca);
            return true;
        }
        return false;
    }

}
