package com.przedtop.bank.system.repozytories;

import com.przedtop.bank.system.entity.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepo extends CrudRepository<Accounts, Long> {
    Accounts findByAccountNumber(long accountNumber);
}
