package com.przedtop.system.bankowy.services;

import com.przedtop.system.bankowy.controllers.model.AccountRequestDataModel;
import com.przedtop.system.bankowy.entity.Accounts;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyAccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private SystemBankowyAccountsRepo repo;

    public Accounts createAccount(AccountRequestDataModel accountRequestDataModel) {
        Accounts accounts = new Accounts();
        accounts.setNrKonta(accountRequestDataModel.getNrKonta());
        accounts.setSaldo(accountRequestDataModel.getSaldo());
        accounts.setData_utworzenia(accountRequestDataModel.getData_zalozenia());
        accounts.setUserId(accountRequestDataModel.getUserId());
        return repo.save(accounts);
    }

    public Accounts getAccount(Long nrKonta) {
        return repo.findById(nrKonta).orElseThrow();
    }

    public void deleteAccount(Long nrKonta) {
        repo.deleteById(nrKonta);
    }

    public Accounts editAccount(AccountRequestDataModel accountRequestDataModel) {
        Accounts accounts = getAccount(accountRequestDataModel.getNrKonta());
        accounts.setNrKonta(accountRequestDataModel.getNrKonta());
        accounts.setSaldo(accountRequestDataModel.getSaldo());
        accounts.setData_utworzenia(accountRequestDataModel.getData_zalozenia());
        return repo.save(accounts);
    }
}
