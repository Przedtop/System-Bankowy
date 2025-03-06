package com.przedtop.system.bankowy.services;

import com.przedtop.system.bankowy.controllers.model.MoneyTransferRequestDataModel;
import com.przedtop.system.bankowy.entity.Accounts;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyAccountsRepo;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferService {


    private final AccountService accountService;
    private final SystemBankowyAccountsRepo repo;

    public MoneyTransferService(AccountService accountService, SystemBankowyAccountsRepo repo) {
        this.accountService = accountService;
        this.repo = repo;
    }

    public boolean moneyTransfer(MoneyTransferRequestDataModel moneyTransferRequestDataModel) {
        Accounts odbiorca = accountService.getAccountByNrKonta(moneyTransferRequestDataModel.getNrKontaOdbiorcy());
        Accounts nadawca = accountService.getAccountByNrKonta(moneyTransferRequestDataModel.getNrKontaNadawcy());

        double saldoNadawcy = accountService.getBalanceByNrKonta(nadawca.getNrKonta());
        double saldoOdbiorcy= accountService.getBalanceByNrKonta(odbiorca.getNrKonta());

        if(saldoNadawcy>=moneyTransferRequestDataModel.getSumaDoPrzelania()){
            odbiorca.setSaldo(saldoOdbiorcy+moneyTransferRequestDataModel.getSumaDoPrzelania());
            nadawca.setSaldo(saldoNadawcy-moneyTransferRequestDataModel.getSumaDoPrzelania());

            repo.save(odbiorca);
            repo.save(nadawca);

            return true;
        }
        return false;
    }

}
