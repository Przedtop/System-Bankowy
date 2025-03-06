package com.przedtop.system.bankowy;

import com.przedtop.system.bankowy.entity.Accounts;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyAccountsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private SystemBankowyAccountsRepo repo;

    @Test
    public void createAccount() {
        Accounts accounts = new Accounts();
        accounts.setNrKonta(9341124);
        accounts.setSaldo(2123);
        accounts.setDataUtworzenia("accountRequestDataModel.getData_zalozenia()");
        accounts.setUserId(5);
        repo.save(accounts);
    }
}