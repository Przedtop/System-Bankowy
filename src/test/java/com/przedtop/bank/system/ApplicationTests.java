package com.przedtop.bank.system;

import com.przedtop.bank.system.entity.Accounts;
import com.przedtop.bank.system.repozytories.AccountsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private AccountsRepo repo;

    @Test
    public void createAccount() {
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(9341124);
        accounts.setBalance(2123);
        accounts.setCreateDate("accountRequestDataModel.getData_zalozenia()");
        accounts.setUserId(5);
        repo.save(accounts);
    }
}