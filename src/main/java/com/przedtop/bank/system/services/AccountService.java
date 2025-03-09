package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.repozytories.AccountsRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
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

        if (accountRequestDataModel.getAccountNumber() != null) {
            if (getAccountByAccountNumber(account.getAccountNumber()) != account) {
                account.setAccountNumber(accountRequestDataModel.getAccountNumber());
            } else {
                Long newAccountNumber = accountNumberGenerator();
                System.out.println("account already exists, new generated value: " + newAccountNumber);
                account.setAccountNumber(newAccountNumber);
            }
        } else
            account.setAccountNumber(accountNumberGenerator());

        if (accountRequestDataModel.getAccountNumber() != null)
            account.setBalance(accountRequestDataModel.getBalance());
        else
            account.setBalance(1000);

        if (accountRequestDataModel.getCreateDate() != null)
            account.setCreateDate(accountRequestDataModel.getCreateDate());
        else {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String data = now.format(formatter);
            account.setCreateDate(data);
        }

        if (accountRequestDataModel.getUserId() != 0)
            account.setUserId(accountRequestDataModel.getUserId());
        else
            account.setUserId(0);

        return repo.save(account);
    }


    public Accounts editAccount(AccountRequestDataModel accountRequestDataModel) {
        Accounts account = getAccountById(accountRequestDataModel.getAccountNumber());

        if (accountRequestDataModel.getAccountNumber() != null)
            account.setAccountNumber(accountRequestDataModel.getAccountNumber());

        if (accountRequestDataModel.getBalance() != 0)
            account.setBalance(accountRequestDataModel.getBalance());

        if (accountRequestDataModel.getCreateDate() != null)
            account.setCreateDate(accountRequestDataModel.getCreateDate());

        return repo.save(account);
    }


    public Accounts getAccountById(Long id) {
        if (id != null)
            try {
                if (repo.findById(id).isPresent())
                    return repo.findById(id).get();
            } catch (NoSuchElementException e) {
                return null;
            }
        return null;
    }


    public Accounts getAccountByAccountNumber(Long accountNumber) {
        if (accountNumber != null)
            try {
                if (repo.findById(accountNumber).isPresent())
                    return repo.findById(accountNumber).get();
            } catch (NoSuchElementException e) {
                return null;
            }
        return null;
    }


    public boolean deleteAccountByID(Long id) {
        if (getAccountById(id) != null) {
            repo.deleteById(id);
            return true;
        } else return false;
    }


    public boolean deleteAccountByAccountNumber(Long accountNumber) {
        if (getAccountByAccountNumber(accountNumber) != null) {
            repo.deleteByAccountNumber((accountNumber));
            return true;
        } else return false;
    }


    public double getBalanceByAccountNumber(Long accountNumber) {
        Accounts account = getAccountByAccountNumber(accountNumber);
        return account.getBalance();
    }
}
