package com.przedtop.system.bankowy.controllers;

import com.przedtop.system.bankowy.controllers.model.UserRequestData;
import com.przedtop.system.bankowy.entity.Users;
import com.przedtop.system.bankowy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Users createUser(@RequestBody UserRequestData userRequestData) {
        System.out.println("request data: " + userRequestData);
        return userService.createUser(userRequestData);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


}

