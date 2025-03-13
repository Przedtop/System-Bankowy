package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.entity.Users;
import jakarta.persistence.Column;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void createAccount_success() {
        Accounts account = new Accounts();
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();

        accountRequestDataModel.setId(123L);
        accountRequestDataModel.setAccountNumber(88912L);
        accountRequestDataModel.setBalance(1000);
        accountRequestDataModel.setCreateDate(LocalDate.now().toString());
        accountRequestDataModel.setUserId(12);

        accountService.createAccount(accountRequestDataModel);

        Assertions.assertEquals(account,account);

        accountService.deleteAccountByAccountNumber(88912L);
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