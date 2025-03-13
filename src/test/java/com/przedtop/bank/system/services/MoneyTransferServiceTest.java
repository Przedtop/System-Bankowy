package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.controllers.model.MoneyTransferRequestDataModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoneyTransferServiceTest {

    @Autowired
    private MoneyTransferService moneyTransferService;
    @Autowired
    private AccountService accountService;
    
    public void createTestAccounts() {
        AccountRequestDataModel accountRequestDataModel = new AccountRequestDataModel();
        if (accountService.getAccountByAccountNumber(100L) == null)
            accountRequestDataModel.setAccountNumber(100L);
        else {
            accountService.deleteAccountByAccountNumber(100L);
            accountRequestDataModel.setAccountNumber(100L);
        }
        accountRequestDataModel.setBalance(1000);
        accountService.createAccount(accountRequestDataModel);


        AccountRequestDataModel accountRequestDataModel2 = new AccountRequestDataModel();
        if (accountService.getAccountByAccountNumber(101L) == null)
            accountRequestDataModel2.setAccountNumber(101L);
        else {
            accountService.deleteAccountByAccountNumber(101L);
            accountRequestDataModel2.setAccountNumber(101L);
        }
        accountRequestDataModel2.setBalance(1000);
        accountService.createAccount(accountRequestDataModel2);
    }

    public void deleteTestAccounts() {
        accountService.deleteAccountByAccountNumber(100L);
        accountService.deleteAccountByAccountNumber(101L);
    }


    @Test
    public void transferMoney_success() {
        createTestAccounts();
        MoneyTransferRequestDataModel requestDataModel = new MoneyTransferRequestDataModel();
        requestDataModel.setAmountToTransfer(100.2);
        requestDataModel.setReceiverAccountNumber(100L);
        requestDataModel.setSenderAccountNumber(101L);

        boolean result = moneyTransferService.moneyTransfer(requestDataModel);

        Assertions.assertTrue(result);
        deleteTestAccounts();
    }

    @Test
    public void transferMoney_SenderAccount_notExist() {
        createTestAccounts();
        MoneyTransferRequestDataModel requestDataModel = new MoneyTransferRequestDataModel();
        requestDataModel.setAmountToTransfer(100.2);
        requestDataModel.setReceiverAccountNumber(100L);
        requestDataModel.setSenderAccountNumber(null);

        boolean result = moneyTransferService.moneyTransfer(requestDataModel);

        Assertions.assertFalse(result);
        deleteTestAccounts();
    }

    @Test
    public void transferMoney_ReceiverAccount_notExist() {
        createTestAccounts();
        MoneyTransferRequestDataModel requestDataModel = new MoneyTransferRequestDataModel();
        requestDataModel.setAmountToTransfer(100.2);
        requestDataModel.setReceiverAccountNumber(null);
        requestDataModel.setSenderAccountNumber(100L);

        boolean result = moneyTransferService.moneyTransfer(requestDataModel);

        Assertions.assertFalse(result);
        deleteTestAccounts();
    }

    @Test
    public void transferMoney_InsufficientBalance() {
        createTestAccounts();
        MoneyTransferRequestDataModel requestDataModel = new MoneyTransferRequestDataModel();
        requestDataModel.setAmountToTransfer(1000.2);
        requestDataModel.setReceiverAccountNumber(100L);
        requestDataModel.setSenderAccountNumber(101L);

        boolean result = moneyTransferService.moneyTransfer(requestDataModel);

        Assertions.assertFalse(result);
        deleteTestAccounts();
    }

    @Test
    public void transferMoney_NullRequest() {
        createTestAccounts();
        MoneyTransferRequestDataModel requestDataModel = new MoneyTransferRequestDataModel();
        requestDataModel.setAmountToTransfer(0);
        requestDataModel.setReceiverAccountNumber(null);
        requestDataModel.setSenderAccountNumber(null);

        boolean result = moneyTransferService.moneyTransfer(requestDataModel);

        Assertions.assertFalse(result);
        deleteTestAccounts();
    }

    @Test
    public void transferMoney_AmountBelowZero() {
        createTestAccounts();
        MoneyTransferRequestDataModel requestDataModel = new MoneyTransferRequestDataModel();
        requestDataModel.setAmountToTransfer(-20.3);
        requestDataModel.setReceiverAccountNumber(100L);
        requestDataModel.setSenderAccountNumber(101L);

        boolean result = moneyTransferService.moneyTransfer(requestDataModel);

        Assertions.assertFalse(result);
        deleteTestAccounts();
    }

    @Test
    public void transferMoney_DepositSuccess() {
        createTestAccounts();
        MoneyTransferRequestDataModel requestDataModel = new MoneyTransferRequestDataModel();
        requestDataModel.setAmountToTransfer(100.65);
        requestDataModel.setReceiverAccountNumber(100L);
        requestDataModel.setSenderAccountNumber(0L);

        boolean result = moneyTransferService.moneyTransfer(requestDataModel);

        Assertions.assertTrue(result);
        deleteTestAccounts();
    }

    @Test
    public void transferMoney_WithdrawSuccess(){
        createTestAccounts();
        MoneyTransferRequestDataModel requestDataModel = new MoneyTransferRequestDataModel();
        requestDataModel.setAmountToTransfer(-100.65);
        requestDataModel.setReceiverAccountNumber(100L);
        requestDataModel.setSenderAccountNumber(0L);

        boolean result = moneyTransferService.moneyTransfer(requestDataModel);

        Assertions.assertTrue(result);
        deleteTestAccounts();
    }
}