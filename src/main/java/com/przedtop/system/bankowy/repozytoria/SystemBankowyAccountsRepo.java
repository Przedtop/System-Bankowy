package com.przedtop.system.bankowy.repozytoria;

import com.przedtop.system.bankowy.entity.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface SystemBankowyAccountsRepo extends CrudRepository<Accounts,Long> {
}
