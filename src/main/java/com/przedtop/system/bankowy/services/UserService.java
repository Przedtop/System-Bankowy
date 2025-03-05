package com.przedtop.system.bankowy.services;

import com.przedtop.system.bankowy.controllers.model.UserRequestDataModel;
import com.przedtop.system.bankowy.controllers.model.UserUpdateDataModel;
import com.przedtop.system.bankowy.entity.Users;
import com.przedtop.system.bankowy.repozytoria.SystemBankowyUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private SystemBankowyUsersRepo repo;

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

    public Users editUserById(UserUpdateDataModel userUpdateDataModel) {
        Users user = getUserById(userUpdateDataModel.getId());
        user.setNazwisko(userUpdateDataModel.getNazwisko());
        user.setImie(userUpdateDataModel.getImie());
        user.setPesel(userUpdateDataModel.getPesel());
        user.setHaslo(userUpdateDataModel.getHaslo());
        user.setLogin(userUpdateDataModel.getLogin());
        return repo.save(user);
    }
}
