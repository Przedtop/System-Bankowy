package com.przedtop.bank.system.services;

import com.przedtop.bank.system.model.AccountDTO;
import com.przedtop.bank.system.model.MoneyTransferDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class MoneyTransferServiceTest {

    @Autowired
    private MoneyTransferService moneyTransferService;
    @Autowired
    private AccountService accountService;
    
    public void createTestAccounts() {
        AccountDTO accountDTO = new AccountDTO();
        if (accountService.getAccountByAccountNumber(100L) == null)
            accountDTO.setAccountNumber(100L);
        else {
            accountService.deleteAccountByAccountNumber(100L);
            accountDTO.setAccountNumber(100L);
        }
        accountDTO.setBalance(1000.0);
        accountService.createAccount(accountDTO);


        AccountDTO accountDTO2 = new AccountDTO();
        if (accountService.getAccountByAccountNumber(101L) == null)
            accountDTO2.setAccountNumber(101L);
        else {
            accountService.deleteAccountByAccountNumber(101L);
            accountDTO2.setAccountNumber(101L);
        }
        accountDTO2.setBalance(1000.0);
        accountService.createAccount(accountDTO2);
    }

    public void deleteTestAccounts() {
        accountService.deleteAccountByAccountNumber(100L);
        accountService.deleteAccountByAccountNumber(101L);
    }


    @Test
    public void transferMoney_success() {
        createTestAccounts();
        MoneyTransferDTO requestDataModel = new MoneyTransferDTO();
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
        MoneyTransferDTO requestDataModel = new MoneyTransferDTO();
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
        MoneyTransferDTO requestDataModel = new MoneyTransferDTO();
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
        MoneyTransferDTO requestDataModel = new MoneyTransferDTO();
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
        MoneyTransferDTO requestDataModel = new MoneyTransferDTO();
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
        MoneyTransferDTO requestDataModel = new MoneyTransferDTO();
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
        MoneyTransferDTO requestDataModel = new MoneyTransferDTO();
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
        MoneyTransferDTO requestDataModel = new MoneyTransferDTO();
        requestDataModel.setAmountToTransfer(-100.65);
        requestDataModel.setReceiverAccountNumber(100L);
        requestDataModel.setSenderAccountNumber(0L);

        boolean result = moneyTransferService.moneyTransfer(requestDataModel);

        Assertions.assertTrue(result);
        deleteTestAccounts();
    }
}