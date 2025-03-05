package com.przedtop.system.bankowy.repozytoria;

import com.przedtop.system.bankowy.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface SystemBankowyUsersRepo extends CrudRepository<Users,Long> {
}
