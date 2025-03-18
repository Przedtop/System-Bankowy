package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.UserRequestDataModel;
import com.przedtop.bank.system.entity.Users;
import com.przedtop.bank.system.repozytories.UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UsersRepo repo;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UsersRepo repo) {
        this.repo = repo;
    }

    public Users createUser(UserRequestDataModel userRequestDataModel) {
        if (userRequestDataModel == null) {
            return null;
        }
        if (userRequestDataModel.getIdentificationNumber() != 0) {
            if (repo.findByIdentificationNumber(userRequestDataModel.getIdentificationNumber()) != null) {
                logger.error("User with this identification number already exists");
                return null;
            }
            Users user = new Users();
            user.setLastName(userRequestDataModel.getLastName());
            user.setName(userRequestDataModel.getName());
            user.setIdentificationNumber(userRequestDataModel.getIdentificationNumber());
            user.setPassword(userRequestDataModel.getPassword());
            user.setLogin(userRequestDataModel.getLogin());
            user.setRole("USER");
            return repo.save(user);
        }
        return null;
    }

    public Users getUserById(Long id) {
        if (id == null) {
            return null;
        }
        try {
            if (repo.findById(id).isPresent())
                return repo.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
        return null;
    }

    public Users getUserByIdentificationNumber(Long identificationNumber) {
        if (identificationNumber == null) {
            return null;
        }
        if (identificationNumber != 0)
            try {
                if (repo.findByIdentificationNumber(identificationNumber) != null)
                    return repo.findByIdentificationNumber(identificationNumber);
            } catch (NoSuchElementException e) {
                logger.error("User not found");
                return null;
            }
        return null;
    }

    public boolean deleteUserById(Long id) {
        if (id == null) {
            return false;
        }
        if (getUserById(id) != null) {
            repo.deleteById(id);
            return true;
        } else return false;
    }

    public boolean deleteUserByIdentificationNumber(Long identificationNumber) {
        if (identificationNumber == null) {
            return false;
        }
        if (getUserByIdentificationNumber(identificationNumber) != null) {
            return deleteUserById(getUserByIdentificationNumber(identificationNumber).getId());
        }
        return false;
    }

    public Users editUserById(UserRequestDataModel userRequestDataModel) {
        if (getUserById(userRequestDataModel.getId()) != null) {
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
        return null;
    }
}
