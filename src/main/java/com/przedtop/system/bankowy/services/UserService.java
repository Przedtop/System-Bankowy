package com.przedtop.system.bankowy.services;

import com.przedtop.system.bankowy.entity.Users;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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

    public void deleteUserById(Long id) {
        repo.deleteById(id);
    }

    public Users editUserById(Long id, String imie, String nazwisko, int pesel, String haslo, String login) {
        Users user = getUserById(id);
        if(!imie.isEmpty()) user.setImie(imie);
        if(!nazwisko.isEmpty())user.setNazwisko(nazwisko);
        if(pesel!=0) user.setPesel(pesel);
        if(!haslo.isEmpty()) user.setHaslo(haslo);
        if(!login.isEmpty()) user.setLogin(login);
        return repo.save(user);
    }
}
