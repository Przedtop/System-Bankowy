package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//http://localhost:8080/api/accounts
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Accounts createAccount(@RequestBody AccountRequestDataModel accountRequestDataModel) {
        System.out.println("POST(/api/accounts) request data: " + accountRequestDataModel);
        return accountService.createAccount(accountRequestDataModel);
    }

    @GetMapping("/{id}")
    public Accounts getAccount(@PathVariable Long id) {
        System.out.println("GET(/api/accounts) request data: " + accountService.getAccountById(id));
        return accountService.getAccountById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {
        System.out.println("DELETE(/api/account) request data: " + accountService.getAccountById(id));
        if (accountService.deleteAccountByIDd(id)) {
            System.out.println("deleted successfully");
            return "deleted successfully";
        } else {
            System.out.println("delete failed");
            return "delete failed";
        }
    }

    @PutMapping
    public Accounts updateAccount(@RequestBody AccountRequestDataModel accountRequestDataModel) {
        System.out.println("PUT(/api/account) request data: " + accountRequestDataModel);
        return accountService.editAccount(accountRequestDataModel);
    }
}

