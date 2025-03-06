package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.UserRequestDataModel;
import com.przedtop.bank.system.entity.Users;
import com.przedtop.bank.system.repozytories.UsersRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepo repo;

    public UserService(UsersRepo repo) {
        this.repo = repo;
    }

    public Users createUser(UserRequestDataModel userRequestDataModel) {
        Users user = new Users();
        user.setLastName(userRequestDataModel.getNazwisko());
        user.setName(userRequestDataModel.getImie());
        user.setIdentificationNumber(userRequestDataModel.getPesel());
        user.setPassword(userRequestDataModel.getHaslo());
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
        user.setLastName(userRequestDataModel.getNazwisko());
        user.setName(userRequestDataModel.getImie());
        user.setIdentificationNumber(userRequestDataModel.getPesel());
        user.setPassword(userRequestDataModel.getHaslo());
        user.setLogin(userRequestDataModel.getLogin());
        return repo.save(user);
    }
}
