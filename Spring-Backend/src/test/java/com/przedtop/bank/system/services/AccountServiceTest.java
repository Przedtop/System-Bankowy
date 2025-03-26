package com.przedtop.bank.system.services;

import com.przedtop.bank.system.model.AccountDTO;
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
        AccountDTO accountDTO = new AccountDTO();
        if (accountService.getAccountByAccountNumber(100L) == null)
            accountDTO.setAccountNumber(100L);
        else {
            accountService.deleteAccountByAccountNumber(100L);
            accountDTO.setAccountNumber(100L);
        }
        accountDTO.setBalance(1000.0);
        accountService.createAccount(accountDTO);
    }

    public void deleteTestAccount() {
        accountService.deleteAccountByAccountNumber(100L);
    }

    //create account
    @Test
    void createAccount_success() {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setAccountNumber(100L);
        accountDTO.setBalance(1000.0);
        accountDTO.setCreateDate(LocalDate.now().toString());
        accountDTO.setUserId(12);

        accountService.createAccount(accountDTO);

        Assertions.assertNotNull(accountService.getAccountByAccountNumber(100L));

        accountService.deleteAccountByAccountNumber(100L);
    }
    @Test
    void createAccount_automaticSuccess() {
        AccountDTO accountDTO = new AccountDTO();

        Accounts account = accountService.createAccount(accountDTO);

        Assertions.assertNotNull(account);

        accountService.deleteAccountByAccountNumber(account.getAccountNumber());
    }
    @Test
    void createAccount_accountRepeat() {
        AccountDTO accountDTO = new AccountDTO();
        AccountDTO accountDTO2 = new AccountDTO();

        accountDTO.setAccountNumber(100L);
        accountDTO2.setAccountNumber(100L);

        Accounts account = accountService.createAccount(accountDTO);
        Accounts account2 = accountService.createAccount(accountDTO2);


        Assertions.assertNotNull(account);
        Assertions.assertNull(account2);

        accountService.deleteAccountByAccountNumber(100L);
    }


    //edit account
    @Test
    void editAccount_success() {
        AccountDTO accountDTO = new AccountDTO();
        AccountDTO accountDTO2 = new AccountDTO();

        accountDTO.setAccountNumber(100L);
        accountDTO2.setAccountNumber(101L);

        Accounts account = accountService.createAccount(accountDTO);

        accountDTO2.setId(account.getId());

        Accounts account2 = accountService.editAccount(accountDTO2);

        Assertions.assertNotNull(account2);

        accountService.deleteAccountByAccountNumber(101L);
    }
    @Test
    void editAccount_accountNotFound() {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId(100L);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.editAccount(accountDTO));

    }
    @Test
    void editAccount_null() {
        AccountDTO accountDTO = new AccountDTO();

        Accounts account = accountService.editAccount(accountDTO);

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
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.getAccountById(null));
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
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.deleteAccountByID(null));
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