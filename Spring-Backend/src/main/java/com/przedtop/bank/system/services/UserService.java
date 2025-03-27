package com.przedtop.bank.system.services;

import com.przedtop.bank.system.entity.Users;
import com.przedtop.bank.system.model.UserDTO;
import com.przedtop.bank.system.repozytories.UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public Users createUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        if (userDTO.getIdentificationNumber() != 0) {
            if (repo.findByIdentificationNumber(userDTO.getIdentificationNumber()) != null) {
                logger.error("User with this identification number already exists");
                throw new IllegalArgumentException("User with this identification number already exists");
            }
            if (repo.findByLogin(userDTO.getLogin()) != null) {
                logger.error("User with this login already exists");
                throw new IllegalArgumentException("User with this login already exists");
            }
            Users user = new Users();
            user.setLastName(userDTO.getLastName());
            user.setName(userDTO.getName());
            user.setIdentificationNumber(userDTO.getIdentificationNumber());
            user.setPassword(userDTO.getPassword());
            user.setLogin(userDTO.getLogin());
            if (userDTO.getRole() != null)
                user.setRole(userDTO.getRole());
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
        if (repo.findById(id).isPresent())
            return repo.findById(id).get();
        return null;
    }

    public Users getUserByIdentificationNumber(Long identificationNumber) {
        if (identificationNumber == null) {
            return null;
        }
        if (identificationNumber != 0)
            if (repo.findByIdentificationNumber(identificationNumber) != null)
                return repo.findByIdentificationNumber(identificationNumber);
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

    public Users editUserById(UserDTO userDTO) {
        if(userDTO == null) {
            return null;
        }
        if (getUserById(userDTO.getId()) != null) {
            Users user = getUserById(userDTO.getId());

            if (userDTO.getLastName() != null)
                user.setLastName(userDTO.getLastName());
            if (userDTO.getName() != null)
                user.setName(userDTO.getName());
            if (userDTO.getIdentificationNumber() != 0)
                user.setIdentificationNumber(userDTO.getIdentificationNumber());
            if (userDTO.getPassword() != null)
                user.setPassword(userDTO.getPassword());
            if (userDTO.getLogin() != null)
                user.setLogin(userDTO.getLogin());
            if (userDTO.getRole() != null)
                user.setRole(userDTO.getRole());
            return repo.save(user);
        }
        return null;
    }
}