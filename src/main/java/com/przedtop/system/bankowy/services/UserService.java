package com.przedtop.system.bankowy.services;

import com.przedtop.system.bankowy.controllers.model.UserRequestDataModel;
import com.przedtop.system.bankowy.entity.Users;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyUsersRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final SystemBankowyUsersRepo repo;

    public UserService(SystemBankowyUsersRepo repo) {
        this.repo = repo;
    }

    public Users createUser(UserRequestDataModel userRequestDataModel) {
        Users user = new Users();
        user.setNazwisko(userRequestDataModel.getNazwisko());
        user.setImie(userRequestDataModel.getImie());
        user.setPesel(userRequestDataModel.getPesel());
        user.setHaslo(userRequestDataModel.getHaslo());
        user.setLogin(userRequestDataModel.getLogin());
        return repo.save(user);
    }

    public Users getUserById(Long id) {
        return repo.findById(id).get();
    }

    public void deleteUserById(Long id) {
        repo.deleteById(id);
    }

    public Users editUserById(UserRequestDataModel userRequestDataModel) {
        Users user = getUserById(userRequestDataModel.getId());
        user.setNazwisko(userRequestDataModel.getNazwisko());
        user.setImie(userRequestDataModel.getImie());
        user.setPesel(userRequestDataModel.getPesel());
        user.setHaslo(userRequestDataModel.getHaslo());
        user.setLogin(userRequestDataModel.getLogin());
        return repo.save(user);
    }
}
