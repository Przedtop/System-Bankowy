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
        user.setLastName(userRequestDataModel.getLastName());
        user.setName(userRequestDataModel.getName());
        user.setIdentificationNumber(userRequestDataModel.getIdentificationNumber());
        user.setPassword(userRequestDataModel.getPassword());
        user.setLogin(userRequestDataModel.getLogin());
        return repo.save(user);
    }

    public Users getUserById(Long id) {
        return repo.findById(id).get();
    }

    public boolean deleteUserById(Long id) {
        if (getUserById(id) != null) {
            repo.deleteById(id);
            return true;
        }
        else return false;
    }

    public Users editUserById(UserRequestDataModel userRequestDataModel) {
        Users user = getUserById(userRequestDataModel.getId());
        user.setLastName(userRequestDataModel.getLastName());
        user.setName(userRequestDataModel.getName());
        user.setIdentificationNumber(userRequestDataModel.getIdentificationNumber());
        user.setPassword(userRequestDataModel.getPassword());
        user.setLogin(userRequestDataModel.getLogin());
        return repo.save(user);
    }
}
