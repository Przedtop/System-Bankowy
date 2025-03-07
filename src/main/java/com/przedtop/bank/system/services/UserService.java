package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.UserRequestDataModel;
import com.przedtop.bank.system.entity.Users;
import com.przedtop.bank.system.repozytories.UsersRepo;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UsersRepo repo;

    public UserService(UsersRepo repo) {
        this.repo = repo;
    }

    public Users createUser(UserRequestDataModel userRequestDataModel) {
        Users user = new Users();
        if (userRequestDataModel.getLastName() != null)
            user.setLastName(userRequestDataModel.getLastName());
        else return null;
        if (userRequestDataModel.getName() != null)
            user.setName(userRequestDataModel.getName());
        else return null;
        if (userRequestDataModel.getIdentificationNumber() != 0)
            user.setIdentificationNumber(userRequestDataModel.getIdentificationNumber());
        else return null;
        if (userRequestDataModel.getPassword() != null)
            user.setPassword(userRequestDataModel.getPassword());
        else return null;
        if (userRequestDataModel.getLogin() != null)
            user.setLogin(userRequestDataModel.getLogin());
        else return null;
        return repo.save(user);
    }

    public Users getUserById(Long id) {
        if (id != null)
            try {
                return repo.findById(id).get();
            } catch (NoSuchElementException e) {
                return null;
            }
        else
            return null;
    }

    public boolean deleteUserById(Long id) {
        if (getUserById(id) != null) {
            repo.deleteById(id);
            return true;
        } else return false;
    }

    public Users editUserById(UserRequestDataModel userRequestDataModel) {
        Users user = getUserById(userRequestDataModel.getId());
        if (userRequestDataModel.getLastName() != null)
            user.setLastName(userRequestDataModel.getLastName());
        if (userRequestDataModel.getName() != null)
            user.setName(userRequestDataModel.getName());
        if (userRequestDataModel.getIdentificationNumber() != 0)
            user.setIdentificationNumber(userRequestDataModel.getIdentificationNumber());
        if (userRequestDataModel.getPassword() != null)
            user.setPassword(userRequestDataModel.getPassword());
        if (userRequestDataModel.getLogin() != null)
            user.setLogin(userRequestDataModel.getLogin());
        return repo.save(user);
    }
}
