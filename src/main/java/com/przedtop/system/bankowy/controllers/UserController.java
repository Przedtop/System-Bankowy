package com.przedtop.system.bankowy.controllers;

import com.przedtop.system.bankowy.controllers.model.UserRequestDataModel;
import com.przedtop.system.bankowy.entity.Users;
import com.przedtop.system.bankowy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Users createUser(@RequestBody UserRequestDataModel userRequestDataModel) {
        System.out.println("request data: " + userRequestDataModel);
        return userService.createUser(userRequestDataModel);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping
    public Users updateUser(@RequestBody UserRequestDataModel userRequestDataModel) {
        System.out.println("update data: " + userRequestDataModel);
        return userService.editUserById(userRequestDataModel);
    }
}

