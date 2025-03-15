package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account controller", description = " Manage accounts in the system")
public class AccountController {

    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    @Operation(summary = "Create account", description = "To generate random value leave it empty, to generate random account use {} i json body.")
    public ResponseEntity<String> createAccount(@RequestBody AccountRequestDataModel accountRequestDataModel) {
        logger.info("Executing createAccount");
        logger.debug("POST(/api/accounts) request data: {}", accountRequestDataModel);
        Accounts account = accountService.createAccount(accountRequestDataModel);
        if (account != null) {
            logger.info("Account created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully\n" + account);
        } else {
            logger.error("Failed to create account");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create account");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    @Operation(summary = "Get account by Id", description = "Returns an account by its Id")
    public ResponseEntity<String> getAccount(@PathVariable Long id) {
        logger.info("Executing getAccount");
        logger.debug("GET(/api/accounts) request data: {}", accountService.getAccountById(id));
        if (accountService.getAccountById(id) != null) {
            logger.info("Account found successfully");
            return ResponseEntity.status(HttpStatus.OK).body("Account found successfully\n" + accountService.getAccountById(id));
        } else {
            logger.error("Invalid request");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid request");
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete account by Id")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        logger.info("Executing deleteAccount");
        logger.debug("DELETE(/api/account) request data: {}", accountService.getAccountById(id));
        if (accountService.deleteAccountByID(id)) {
            logger.info("Deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            logger.error("Delete failed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("delete failed");
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/accountNumber/{accountNumber}")
    @Operation(summary = "Delete account by account number")
    public ResponseEntity<String> deleteAccountByAccountNumber(@PathVariable Long accountNumber) {
        logger.info("Executing deleteAccountByAccountNumber");
        logger.debug("DELETE(/api/account/accountNumber) request data: {}", accountService.getAccountByAccountNumber(accountNumber));
        if (accountService.deleteAccountByAccountNumber(accountNumber)) {
            logger.info("Deleted successfully!");
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            logger.error("Delete failed!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("delete failed");
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    @Operation(summary = "Update account")
    public ResponseEntity<String> updateAccount(@RequestBody AccountRequestDataModel accountRequestDataModel) {
        logger.info("Executing updateAccount");
        logger.debug("PUT(/api/account) request data: {}", accountRequestDataModel);

        if (accountRequestDataModel.getId() == null) {
            logger.error("Update account failed because id is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Accounts account = accountService.editAccount(accountRequestDataModel);
        if (account != null) {
            logger.info("Account updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body("Account updated successfully\n" + account);
        } else {
            logger.error("Failed to update account");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("/{oldId}/{newId}")
    @Operation(summary = "Change account id")
    public ResponseEntity<String> changeAccountId(@PathVariable Long oldId, @PathVariable Long newId) {
        logger.info("Executing changeAccountId");

        if (newId != 0) {
            if (accountService.editAccountId(oldId, newId)) {
                logger.info("Account id changed successfully");
                return ResponseEntity.status(HttpStatus.OK).body("Account id changed successfully");
            } else {
                logger.error("Account not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
            }
        } else {
            logger.error("Account id can't be equal 0");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account id can't be equal 0");
        }
    }
}

