package com.przedtop.system.bankowy.datamanage;

import com.przedtop.system.bankowy.entity.Users;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserData {

    @Autowired
    private SystemBankowyUsersRepo repo;

    public void createUser(String imie, String nazwisko, int pesel, String haslo, String login) {
        Users user = new Users();
        user.setNazwisko(nazwisko);
        user.setImie(imie);
        user.setPesel(pesel);
        user.setHaslo(haslo);
        user.setLogin(login);
        repo.save(user);
    }

    public Users getUserById(Long id) {
        return repo.findById(id).get();
    }
}
