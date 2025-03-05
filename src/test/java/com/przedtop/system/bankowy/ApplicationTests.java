package com.przedtop.system.bankowy;

import com.przedtop.system.bankowy.controllers.model.AccountRequestDataModel;
import com.przedtop.system.bankowy.entity.Accounts;
import com.przedtop.system.bankowy.entity.Users;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyAccountsRepo;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyUsersRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private SystemBankowyAccountsRepo repo;

    @Test
    public void createAccount() {
        Accounts accounts = new Accounts();
        accounts.setNrKonta(9341124);
        accounts.setSaldo(2123);
        accounts.setData_utworzenia("accountRequestDataModel.getData_zalozenia()");
        repo.save(accounts);
    }
}