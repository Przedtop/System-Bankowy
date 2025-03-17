package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.repozytories.AccountsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class AccountService {

    private final AccountsRepo repo;

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    public AccountService(AccountsRepo repo) {
        this.repo = repo;
    }

    public Long accountNumberGenerator() {
        Random rand = new Random();

        long newNumber;
        do {
            newNumber = rand.nextInt(1000000000) + 100000000;
        } while (getAccountByAccountNumber(newNumber) != null);

        return newNumber;
    }

    public Accounts createAccount(AccountRequestDataModel accountRequestDataModel) {
        try {
            Accounts account = new Accounts();

            if (accountRequestDataModel.getAccountNumber() != null) {
                if (accountRequestDataModel.getAccountNumber() != 0) {
                    if (getAccountByAccountNumber(accountRequestDataModel.getAccountNumber()) == null) {
                        account.setAccountNumber(accountRequestDataModel.getAccountNumber());
                    } else {
                        logger.error("Account already exists");
                        return null;
                    }
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

            repo.save(account);
            return account;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }


    public Accounts editAccount(AccountRequestDataModel accountRequestDataModel) {
        if (accountRequestDataModel.getId() != null) {
            if (getAccountById(accountRequestDataModel.getId()) != null) {
                Accounts account = getAccountById(accountRequestDataModel.getId());
                if (accountRequestDataModel.getAccountNumber() != null)
                    account.setAccountNumber(accountRequestDataModel.getAccountNumber());

                if (accountRequestDataModel.getBalance() != 0)
                    account.setBalance(accountRequestDataModel.getBalance());

                if (accountRequestDataModel.getCreateDate() != null)
                    account.setCreateDate(accountRequestDataModel.getCreateDate());

                return repo.save(account);
            }
        }
        return null;
    }


    public Accounts getAccountById(Long id) {
        if (id != null)
            try {
                if (repo.findById(id).isPresent())
                    return repo.findById(id).get();
            } catch (NoSuchElementException e) {
                logger.error(e.getMessage());
                return null;
            }
        return null;
    }
    public Accounts getAccountByAccountNumber(Long accountNumber) {
        if (accountNumber != null) {
            try {
                if (repo.findByAccountNumber(accountNumber) != null)
                    return repo.findByAccountNumber(accountNumber);
            } catch (NoSuchElementException e) {
                return null;
            }
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
            return deleteAccountByID(getAccountByAccountNumber(accountNumber).getId());
        } else return false;
    }


    public double getBalanceByAccountNumber(Long accountNumber) {
        if (accountNumber != null && getAccountByAccountNumber(accountNumber) != null) {
            Accounts account = getAccountByAccountNumber(accountNumber);
            return account.getBalance();
        }
        return 0;
    }
    public double getBalanceById(Long id) {
        if (id != null && getAccountById(id) != null) {
            Accounts account = getAccountById(id);
            return account.getBalance();
        }
        return 0;
    }
}
