package com.przedtop.system.bankowy.controllers;

import com.przedtop.system.bankowy.controllers.model.AccountRequestDataModel;
import com.przedtop.system.bankowy.entity.Accounts;
import com.przedtop.system.bankowy.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Accounts createAccount(@RequestBody AccountRequestDataModel accountRequestDataModel) {
        System.out.println("request data: " + accountRequestDataModel);
        return accountService.createAccount(accountRequestDataModel);
    }

    @GetMapping("/{id}")
    public Accounts getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @GetMapping("/update")
    public Accounts updateAccount(@RequestBody AccountRequestDataModel accountRequestDataModel) {
        System.out.println("update data: " + accountRequestDataModel);
        return accountService.editAccount(accountRequestDataModel);
    }
}

