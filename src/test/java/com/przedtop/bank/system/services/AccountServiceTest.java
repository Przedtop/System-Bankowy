package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void createAccount_success() {
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();

        accountRequestDataModel.setAccountNumber(88912L);
        accountRequestDataModel.setBalance(1000);
        accountRequestDataModel.setCreateDate(LocalDate.now().toString());
        accountRequestDataModel.setUserId(12);

        accountService.createAccount(accountRequestDataModel);

        Assertions.assertNotNull(accountService.getAccountByAccountNumber(88912L));

        accountService.deleteAccountByAccountNumber(88912L);
    }

    @Test
    void createAccount_automaticSuccess() {
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();

        Accounts account = accountService.createAccount(accountRequestDataModel);

        Assertions.assertNotNull(account);

        accountService.deleteAccountByAccountNumber(account.getAccountNumber());
    }

    @Test
    void editAccount() {

    }

    @Test
    void getAccountById() {
    }

    @Test
    void getAccountByAccountNumber() {
    }

    @Test
    void deleteAccountByID() {
    }

    @Test
    void deleteAccountByAccountNumber() {
    }

    @Test
    void getBalanceByAccountNumber() {
    }
}