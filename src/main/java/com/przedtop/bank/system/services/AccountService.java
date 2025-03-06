package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.repozytories.AccountsRepo;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {

    private final AccountsRepo repo;

    public AccountService(AccountsRepo repo) {
        this.repo = repo;
    }

    private Long accountNumberGenerator() {
        Random rand = new Random();
        return rand.nextLong(1000000000) + 100000000;
    }

    public Accounts createAccount(AccountRequestDataModel accountRequestDataModel) {
        Accounts account = new Accounts();
        if (accountRequestDataModel.getNrKonta() != null) {
            if (getAccountByAccountNumber(account.getAccountNumber()) != account) {
                account.setAccountNumber(accountRequestDataModel.getNrKonta());
            } else {
                Long newAccountNumber = accountNumberGenerator();
                System.out.println("account already exists, new generated value: " + newAccountNumber);
                account.setAccountNumber(newAccountNumber);
            }
        } else {
            account.setAccountNumber(accountNumberGenerator());
        }
        account.setBalance(accountRequestDataModel.getSaldo());
        account.setCreateDate(accountRequestDataModel.getDataUtworzenia());
        account.setUserId(accountRequestDataModel.getUserId());
        return repo.save(account);
    }

    public Accounts getAccountById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Accounts getAccountByAccountNumber(Long nrKonta) {
        return repo.findByNrKonta(nrKonta);
    }

    public void deleteAccountByIDd(Long id) {
        repo.deleteById(id);
    }

    public void deleteAccountByAccountNumber(Long nrKonta) {
        repo.deleteByNrKonta(nrKonta);
    }

    public double getBalanceByAccountNumber(Long nrKonta) {
        Accounts account = getAccountByAccountNumber(nrKonta);
        return account.getBalance();
    }

    public Accounts editAccount(AccountRequestDataModel accountRequestDataModel) {
        Accounts account = getAccountById(accountRequestDataModel.getNrKonta());
        account.setAccountNumber(accountRequestDataModel.getNrKonta());
        account.setBalance(accountRequestDataModel.getSaldo());
        account.setCreateDate(accountRequestDataModel.getDataUtworzenia());
        return repo.save(account);
    }
}
