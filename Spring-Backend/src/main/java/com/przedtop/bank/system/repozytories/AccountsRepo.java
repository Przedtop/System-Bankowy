package com.przedtop.bank.system.repozytories;

import com.przedtop.bank.system.entity.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepo extends CrudRepository<Accounts, Long> {
    Accounts findByAccountNumber(long accountNumber);
}
