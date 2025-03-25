package com.przedtop.bank.system.repozytories;

import com.przedtop.bank.system.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends CrudRepository<Users, Long> {
    Users findByLogin(String Login);
    Users findByIdentificationNumber(Long identificationNumber);
}
