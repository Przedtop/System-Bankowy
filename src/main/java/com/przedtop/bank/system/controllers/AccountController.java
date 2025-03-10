package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//http://localhost:8080/api/accounts
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Accounts> createAccount(@RequestBody AccountRequestDataModel accountRequestDataModel) {
        logger.info("Executing createAccount");
        logger.debug("POST(/api/accounts) request data: {}", accountRequestDataModel);
        Accounts account = accountService.createAccount(accountRequestDataModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accounts> getAccount(@PathVariable Long id) {
        logger.info("Executing getAccount");
        if (accountService.getAccountById(id) != null) {
            logger.debug("GET(/api/accounts) request data: {}", accountService.getAccountById(id));
            return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountById(id));
        } else {
            logger.warn("GET(/api/accounts) invalid request");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        logger.info("Executing deleteAccount");
        logger.debug("DELETE(/api/account) request data: " + accountService.getAccountById(id));
        if (accountService.deleteAccountByID(id)) {
            logger.info("deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            logger.warn("delete failed");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("delete failed");
        }
    }

    @DeleteMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<String> deleteAccountByAccountNumber(@PathVariable Long accountNumber) {
        logger.info("Executing deleteAccountByAccountNumber");
        logger.debug("DELETE(/api/account/accountNumber) request data: " + accountService.getAccountByAccountNumber(accountNumber));
        if (accountService.deleteAccountByAccountNumber(accountNumber)) {
            logger.info("deleted successfully1");
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            logger.warn("delete failed1");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("delete failed");
        }
    }

    @PutMapping
    public ResponseEntity<Accounts> updateAccount(@RequestBody AccountRequestDataModel accountRequestDataModel) {
        logger.info("Executing updateAccount");
        logger.debug("PUT(/api/account) request data: {}", accountRequestDataModel);
        Accounts account = accountService.editAccount(accountRequestDataModel);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }
}

