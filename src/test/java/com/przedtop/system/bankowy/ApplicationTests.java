package com.przedtop.system.bankowy;

import com.przedtop.system.bankowy.entity.Users;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyUsersRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private SystemBankowyUsersRepo repo;

    @Test
    void createUserTest() {
        Users user = new Users();
        user.setNazwisko("Nazwis");
        user.setImie("Imie");
        user.setPesel(1234);
        user.setHaslo("Haslo");
        user.setLogin("Login");
        repo.save(user);
    }

    @Test
    void getUserTest() {
        Users users = repo.findById(1L).get();
        System.out.println(users);
    }
}