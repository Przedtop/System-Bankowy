package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.UserRequestDataModel;
import com.przedtop.bank.system.entity.Users;
import com.przedtop.bank.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Users createUser(@RequestBody UserRequestDataModel userRequestDataModel) {
        System.out.println("POST(/api/users) request data: " + userRequestDataModel);
        return userService.createUser(userRequestDataModel);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        System.out.println("GET(/api/users) request data: " + userService.getUserById(id));
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        System.out.println("DELETE(/api/users) request data: " + userService.getUserById(id));
        if(userService.deleteUserById(id)){
            System.out.println("deleted successfully");
            return "deleted successfully";
        } else {
            System.out.println("delete failed");
            return "delete failed";
        }
    }

    @PutMapping
    public Users updateUser(@RequestBody UserRequestDataModel userRequestDataModel) {
        System.out.println("PUT(/api/users) request data: " + userRequestDataModel);
        return userService.editUserById(userRequestDataModel);
    }
}

