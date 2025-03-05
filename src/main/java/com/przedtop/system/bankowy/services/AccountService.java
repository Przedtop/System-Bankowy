package com.przedtop.system.bankowy.services;

import com.przedtop.system.bankowy.entity.Accounts;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyAccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private SystemBankowyAccountsRepo repo;

    public void createAccount(long nrKonta, double saldo, String dataZalozenia) {
        Accounts accounts = new Accounts();
        accounts.setNrKonta(nrKonta);
        accounts.setSaldo(saldo);
        accounts.setData_utworzenia(dataZalozenia);
        repo.save(accounts);
    }

    public Accounts getAccount(Long nrKonta) {
        return repo.findById(nrKonta).get();
    }

    public void deleteAccount(Long nrKonta) {
        repo.deleteById(nrKonta);
    }

    public Accounts editAccount(long nrKonta, double saldo, String dataZalozenia) {
        Accounts accounts = getAccount(nrKonta);
        accounts.setSaldo(saldo);
        if(!dataZalozenia.isEmpty()) accounts.setData_utworzenia(dataZalozenia);
        repo.save(accounts);
        return repo.save(accounts);
    }
}
