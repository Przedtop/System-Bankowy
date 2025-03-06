package com.przedtop.system.bankowy.services;

import com.przedtop.system.bankowy.controllers.model.AccountRequestDataModel;
import com.przedtop.system.bankowy.entity.Accounts;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyAccountsRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final SystemBankowyAccountsRepo repo;

    public AccountService(SystemBankowyAccountsRepo repo) {
        this.repo = repo;
    }

    public Accounts createAccount(AccountRequestDataModel accountRequestDataModel) {
        Accounts account = new Accounts();
        account.setNrKonta(accountRequestDataModel.getNrKonta());
        account.setSaldo(accountRequestDataModel.getSaldo());
        account.setDataUtworzenia(accountRequestDataModel.getDataUtworzenia());
        account.setUserId(accountRequestDataModel.getUserId());
        return repo.save(account);
    }

    public Accounts getAccountById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Accounts getAccountByNrKonta(Long nrKonta) {
        return repo.findByNrKonta(nrKonta);
    }

    public void deleteAccountByIDd(Long id) {
        repo.deleteById(id);
    }

    public void deleteAccountByNrKonta(Long nrKonta) {
        repo.deleteByNrKonta(nrKonta);
    }

    public double getBalanceByNrKonta(Long nrKonta) {
        Accounts account = getAccountByNrKonta(nrKonta);
        return account.getSaldo();
    }

    public Accounts editAccount(AccountRequestDataModel accountRequestDataModel) {
        Accounts account = getAccountById(accountRequestDataModel.getNrKonta());
        account.setNrKonta(accountRequestDataModel.getNrKonta());
        account.setSaldo(accountRequestDataModel.getSaldo());
        account.setDataUtworzenia(accountRequestDataModel.getDataUtworzenia());
        return repo.save(account);
    }
}
