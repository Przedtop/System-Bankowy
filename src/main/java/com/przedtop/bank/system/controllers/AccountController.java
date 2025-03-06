package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.AccountRequestDataModel;
import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Accounts createAccount(@RequestBody AccountRequestDataModel accountRequestDataModel) {
        System.out.println("create account data:" + accountRequestDataModel);
        return accountService.createAccount(accountRequestDataModel);
    }

    @GetMapping("/{id}")
    public Accounts getAccount(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccountByIDd(id);
    }

    @PutMapping
    public Accounts updateAccount(@RequestBody AccountRequestDataModel accountRequestDataModel) {
        System.out.println("update data: " + accountRequestDataModel);
        return accountService.editAccount(accountRequestDataModel);
    }
}

