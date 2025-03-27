package com.przedtop.bank.system.services;

import com.przedtop.bank.system.model.AccountDTO;
import com.przedtop.bank.system.model.MoneyTransferDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class MoneyTransferServiceTest {

    @Autowired
    private MoneyTransferService moneyTransferService;
    @Autowired
    private AccountService accountService;

    @BeforeEach
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

    @AfterEach
    public void deleteTestAccounts() {
        accountService.deleteAccountByAccountNumber(100L);
        accountService.deleteAccountByAccountNumber(101L);
    }


    @Test
    public void testMoneyTransferSuccess() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(100L);
        moneyTransferDTO.setReceiverAccountNumber(101L);
        moneyTransferDTO.setAmountToTransfer(100.0);

        moneyTransferService.moneyTransfer(moneyTransferDTO);

        assertEquals(900.0, accountService.getAccountByAccountNumber(100L).getBalance());
        assertEquals(1100.0, accountService.getAccountByAccountNumber(101L).getBalance());
    }
    @Test
    public void testMoneyTransferToYourself() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(100L);
        moneyTransferDTO.setReceiverAccountNumber(100L);
        moneyTransferDTO.setAmountToTransfer(100.0);

        try {
            moneyTransferService.moneyTransfer(moneyTransferDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("You can't transfer money to yourself", e.getMessage());
        }
    }
    @Test
    public void testMoneyTransferNotEnoughMoney() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(100L);
        moneyTransferDTO.setReceiverAccountNumber(101L);
        moneyTransferDTO.setAmountToTransfer(10000.0);

        try {
            moneyTransferService.moneyTransfer(moneyTransferDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("Not enough money on sender account", e.getMessage());
        }
    }
    @Test
    public void testMoneyTransferAccountNotFound() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(100L);
        moneyTransferDTO.setReceiverAccountNumber(102L);
        moneyTransferDTO.setAmountToTransfer(100.0);

        try {
            moneyTransferService.moneyTransfer(moneyTransferDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("Account not found", e.getMessage());
        }
    }
    @Test
    public void testMoneyTransferAmountZero() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(100L);
        moneyTransferDTO.setReceiverAccountNumber(101L);
        moneyTransferDTO.setAmountToTransfer(0.0);

        try {
            moneyTransferService.moneyTransfer(moneyTransferDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("Amount to transfer can't be equal to 0", e.getMessage());
        }
    }

    @Test
    public void testMoneyTransfer_WithdrawSuccess() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(0L);
        moneyTransferDTO.setReceiverAccountNumber(100L);
        moneyTransferDTO.setAmountToTransfer(-100.0);

        moneyTransferService.moneyTransfer(moneyTransferDTO);

        assertEquals(900.0, accountService.getAccountByAccountNumber(100L).getBalance());
    }
    @Test
    public void testMoneyTransfer_WithdrawAccountNotFound() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(0L);
        moneyTransferDTO.setReceiverAccountNumber(102L);
        moneyTransferDTO.setAmountToTransfer(-100.0);

        try {
            moneyTransferService.moneyTransfer(moneyTransferDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("Account not found", e.getMessage());
        }
    }
    @Test
    public void testMoneyTransfer_WithdrawAmountZero() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(0L);
        moneyTransferDTO.setReceiverAccountNumber(100L);
        moneyTransferDTO.setAmountToTransfer(0.0);

        try {
            moneyTransferService.moneyTransfer(moneyTransferDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("Amount can't be equal to 0", e.getMessage());
        }
    }

    @Test
    public void testMoneyTransfer_DepositSuccess() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(0L);
        moneyTransferDTO.setReceiverAccountNumber(100L);
        moneyTransferDTO.setAmountToTransfer(100.0);

        moneyTransferService.moneyTransfer(moneyTransferDTO);

        assertEquals(1100.0, accountService.getAccountByAccountNumber(100L).getBalance());
    }
    @Test
    public void testMoneyTransfer_DepositAccountNotFound() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(0L);
        moneyTransferDTO.setReceiverAccountNumber(102L);
        moneyTransferDTO.setAmountToTransfer(100.0);

        try {
            moneyTransferService.moneyTransfer(moneyTransferDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("Account not found", e.getMessage());
        }
    }
    @Test
    public void testMoneyTransfer_DepositAmountZero() {
        MoneyTransferDTO moneyTransferDTO = new MoneyTransferDTO();
        moneyTransferDTO.setSenderAccountNumber(0L);
        moneyTransferDTO.setReceiverAccountNumber(100L);
        moneyTransferDTO.setAmountToTransfer(0.0);

        try {
            moneyTransferService.moneyTransfer(moneyTransferDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("Amount can't be equal to 0", e.getMessage());
        }
    }
}