package com.przedtop.bank.system.services;

import com.przedtop.bank.system.model.AccountDTO;
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
            newNumber = rand.nextInt(1000000000) + 90000000;
        } while (getAccountByAccountNumber(newNumber) != null);

        return newNumber;
    }

    public Accounts createAccount(AccountDTO accountDTO) {
        try {
            Accounts account = new Accounts();

            if (accountDTO.getAccountNumber() != null) {
                if (accountDTO.getAccountNumber() != 0) {
                    if (getAccountByAccountNumber(accountDTO.getAccountNumber()) == null) {
                        account.setAccountNumber(accountDTO.getAccountNumber());
                    } else {
                        throw new IllegalArgumentException("Account with this account number already exists");
                    }
                }
            } else
                account.setAccountNumber(accountNumberGenerator());

            if (accountDTO.getBalance() != null)
                account.setBalance(accountDTO.getBalance());
            else
                account.setBalance(1000);

            if (accountDTO.getCreateDate() != null)
                account.setCreateDate(accountDTO.getCreateDate());
            else {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String data = now.format(formatter);
                account.setCreateDate(data);
            }

            if (accountDTO.getUserId() != 0)
                account.setUserId(accountDTO.getUserId());
            else
                account.setUserId(0);

            repo.save(account);
            return account;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }


    public Accounts editAccount(AccountDTO accountDTO) {
        if (accountDTO.getId() != null) {
            if (getAccountById(accountDTO.getId()) != null) {
                if (getAccountByAccountNumber(accountDTO.getAccountNumber()) == null) {

                    if (getAccountById(accountDTO.getId()) != null) {
                        Accounts account = getAccountById(accountDTO.getId());

                        if (accountDTO.getAccountNumber() != null)
                            account.setAccountNumber(accountDTO.getAccountNumber());

                        if (accountDTO.getBalance() != null)
                            account.setBalance(accountDTO.getBalance());

                        if (accountDTO.getCreateDate() != null)
                            account.setCreateDate(accountDTO.getCreateDate());

                        if (accountDTO.getUserId() != 0)
                            account.setUserId(accountDTO.getUserId());

                        return repo.save(account);
                    }
                } else {
                    throw new IllegalArgumentException("Account with this account number already exists");
                }
            } else {
                throw new IllegalArgumentException("Account with this id does not exist");
            }
        }
        return null;
    }


    public Accounts getAccountById(Long id) {
        if (id != null) {
            try {
                if (repo.findById(id).isPresent())
                    return repo.findById(id).get();
            } catch (NoSuchElementException e) {
                logger.error(e.getMessage());
                return null;
            }
        } else {
            throw new IllegalArgumentException("Id is null");
        }
        return null;
    }

    public Accounts getAccountByAccountNumber(Long accountNumber) {
        if (accountNumber != null) {
            try {
                if (repo.findByAccountNumber(accountNumber) != null)
                    return repo.findByAccountNumber(accountNumber);
            } catch (NoSuchElementException e) {
                logger.error(e.getMessage());
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
