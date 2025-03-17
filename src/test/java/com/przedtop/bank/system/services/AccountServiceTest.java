package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@ActiveProfiles("test")
@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    public void createTestAccount() {
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();
        if (accountService.getAccountByAccountNumber(100L) == null)
            accountRequestDataModel.setAccountNumber(100L);
        else {
            accountService.deleteAccountByAccountNumber(100L);
            accountRequestDataModel.setAccountNumber(100L);
        }
        accountRequestDataModel.setBalance(1000);
        accountService.createAccount(accountRequestDataModel);
    }

    public void deleteTestAccount() {
        accountService.deleteAccountByAccountNumber(100L);
    }

    //create account
    @Test
    void createAccount_success() {
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();

        accountRequestDataModel.setAccountNumber(100L);
        accountRequestDataModel.setBalance(1000);
        accountRequestDataModel.setCreateDate(LocalDate.now().toString());
        accountRequestDataModel.setUserId(12);

        accountService.createAccount(accountRequestDataModel);

        Assertions.assertNotNull(accountService.getAccountByAccountNumber(100L));

        accountService.deleteAccountByAccountNumber(100L);
    }
    @Test
    void createAccount_automaticSuccess() {
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();

        Accounts account = accountService.createAccount(accountRequestDataModel);

        Assertions.assertNotNull(account);

        accountService.deleteAccountByAccountNumber(account.getAccountNumber());
    }
    @Test
    void createAccount_accountRepeat() {
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();
        AccountRequestDataModel accountRequestDataModel2 = new AccountRequestDataModel();

        accountRequestDataModel.setAccountNumber(100L);
        accountRequestDataModel2.setAccountNumber(100L);

        Accounts account = accountService.createAccount(accountRequestDataModel);
        Accounts account2 = accountService.createAccount(accountRequestDataModel2);


        Assertions.assertNotNull(account);
        Assertions.assertNull(account2);

        accountService.deleteAccountByAccountNumber(100L);
    }


    //edit account
    @Test
    void editAccount_success() {
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();
        AccountRequestDataModel accountRequestDataModel2 = new AccountRequestDataModel();

        accountRequestDataModel.setAccountNumber(100L);
        accountRequestDataModel2.setAccountNumber(101L);

        Accounts account = accountService.createAccount(accountRequestDataModel);

        accountRequestDataModel2.setId(account.getId());

        Accounts account2 = accountService.editAccount(accountRequestDataModel2);

        Assertions.assertNotNull(account2);

        accountService.deleteAccountByAccountNumber(101L);
    }
    @Test
    void editAccount_accountNotFound() {
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();

        accountRequestDataModel.setId(100L);

        Accounts account = accountService.editAccount(accountRequestDataModel);

        Assertions.assertNull(account);
    }
    @Test
    void editAccount_null() {
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();

        Accounts account = accountService.editAccount(accountRequestDataModel);

        Assertions.assertNull(account);
    }


    //get account by id
    @Test
    void getAccountById_success() {
        createTestAccount();

        Accounts account = accountService.getAccountByAccountNumber(100L);

        Assertions.assertNotNull(accountService.getAccountById(account.getId()));

        deleteTestAccount();
    }
    @Test
    void getAccountById_accountNotFound() {
        Accounts account = accountService.getAccountById(100L);

        Assertions.assertNull(account);
    }
    @Test
    void getAccountById_null() {
        Accounts account = accountService.getAccountById(null);

        Assertions.assertNull(account);
    }


    //get account by account number
    @Test
    void getAccountByAccountNumber_success() {
        createTestAccount();

        Accounts account = accountService.getAccountByAccountNumber(100L);

        Assertions.assertNotNull(account);

        deleteTestAccount();
    }
    @Test
    void getAccountByAccountNumber_accountNotFound() {
        Accounts account = accountService.getAccountByAccountNumber(100L);

        Assertions.assertNull(account);
    }
    @Test
    void getAccountByAccountNumber_null() {
        Accounts account = accountService.getAccountByAccountNumber(null);

        Assertions.assertNull(account);
    }


    //delete account by id
    @Test
    void deleteAccountByID_success() {
        createTestAccount();

        Accounts account = accountService.getAccountByAccountNumber(100L);

        Assertions.assertTrue(accountService.deleteAccountByID(account.getId()));

        deleteTestAccount();
    }
    @Test
    void deleteAccountByID_accountNotFound() {
        Assertions.assertFalse(accountService.deleteAccountByID(100L));
    }
    @Test
    void deleteAccountByID_null() {
        Assertions.assertFalse(accountService.deleteAccountByID(null));
    }


    //delete account by account number
    @Test
    void deleteAccountByAccountNumber_success() {
        createTestAccount();

        Assertions.assertTrue(accountService.deleteAccountByAccountNumber(100L));

        deleteTestAccount();
    }
    @Test
    void deleteAccountByAccountNumber_accountNotFound() {
        Assertions.assertFalse(accountService.deleteAccountByAccountNumber(100L));
    }
    @Test
    void deleteAccountByAccountNumber_null() {
        Assertions.assertFalse(accountService.deleteAccountByAccountNumber(null));
    }


    //get balance by account number
    @Test
    void getBalanceByAccountNumber_success() {
        createTestAccount();

        Assertions.assertEquals(1000, accountService.getBalanceByAccountNumber(100L));

        deleteTestAccount();
    }
    @Test
    void getBalanceByAccountNumber_accountNotFound() {
        Assertions.assertEquals(0, accountService.getBalanceByAccountNumber(100L));
    }
    @Test
    void getBalanceByAccountNumber_null() {
        Assertions.assertEquals(0, accountService.getBalanceByAccountNumber(null));
    }


    //get balance by id
    @Test
    void getBalanceById_success() {
        createTestAccount();

        Accounts account = accountService.getAccountByAccountNumber(100L);

        Assertions.assertEquals(1000, accountService.getBalanceById(account.getId()));

        deleteTestAccount();
    }
    @Test
    void getBalanceById_accountNotFound() {
        Assertions.assertEquals(0, accountService.getBalanceById(100L));
    }
    @Test
    void getBalanceById_null() {
        Assertions.assertEquals(0, accountService.getBalanceById(null));
    }
}