package com.przedtop.bank.system.repozytories;

import com.przedtop.bank.system.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users, Long> {
}
