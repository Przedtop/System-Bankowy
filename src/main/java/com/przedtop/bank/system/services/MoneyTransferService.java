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
        Accounts odbiorca = accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getNrKontaOdbiorcy());
        Accounts nadawca = accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getNrKontaNadawcy());

        double saldoNadawcy = accountService.getBalanceByAccountNumber(nadawca.getAccountNumber());
        double saldoOdbiorcy = accountService.getBalanceByAccountNumber(odbiorca.getAccountNumber());

        if (saldoNadawcy >= moneyTransferRequestDataModel.getSumaDoPrzelania()) {
            odbiorca.setBalance(saldoOdbiorcy + moneyTransferRequestDataModel.getSumaDoPrzelania());
            nadawca.setBalance(saldoNadawcy - moneyTransferRequestDataModel.getSumaDoPrzelania());

            repo.save(odbiorca);
            repo.save(nadawca);

            return true;
        }
        return false;
    }

}
