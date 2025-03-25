package com.przedtop.bank.system.services;

import com.przedtop.bank.system.entity.Users;
import com.przedtop.bank.system.model.UserRequestDataModel;
import com.przedtop.bank.system.repozytories.UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UsersRepo repo;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UsersRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByLogin(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                getAuthorities(user.getRole())
        );
    }

    private Set<SimpleGrantedAuthority> getAuthorities(String role) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public Users createUser(UserRequestDataModel userRequestDataModel) {
        if (userRequestDataModel == null) {
            return null;
        }
        if (userRequestDataModel.getIdentificationNumber() != 0) {
            if (repo.findByIdentificationNumber(userRequestDataModel.getIdentificationNumber()) != null) {
                logger.error("User with this identification number already exists");
                throw new IllegalArgumentException("User with this identification number already exists");
            }
            if(repo.findByLogin(userRequestDataModel.getLogin()) != null) {
                logger.error("User with this login already exists");
                throw new IllegalArgumentException("User with this login already exists");
            }
            Users user = new Users();
            user.setLastName(userRequestDataModel.getLastName());
            user.setName(userRequestDataModel.getName());
            user.setIdentificationNumber(userRequestDataModel.getIdentificationNumber());
            user.setPassword(userRequestDataModel.getPassword());
            user.setLogin(userRequestDataModel.getLogin());
            if (userRequestDataModel.getRole() != null)
                user.setRole(userRequestDataModel.getRole());
            else
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