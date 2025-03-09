package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.UserRequestDataModel;
import com.przedtop.bank.system.entity.Users;
import com.przedtop.bank.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequestDataModel userRequestDataModel) {
        System.out.println("POST(/api/users) request data: " + userRequestDataModel);
        Users user = userService.createUser(userRequestDataModel);
        if (user != null) {
            System.out.println("User created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } else {
            System.out.println("Failed to create user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        System.out.println("GET(/api/users) request data: " + userService.getUserById(id));
        Users user = userService.getUserById(id);
        if (user != null) {
            System.out.println("User found successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(user);
        } else {
            System.out.println("Failed to create user");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        System.out.println("DELETE(/api/users) request data: " + userService.getUserById(id));
        if (userService.deleteUserById(id)) {
            System.out.println("deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            System.out.println("delete failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("delete failed");
        }
    }

    @PutMapping
    public ResponseEntity<Users> updateUser(@RequestBody UserRequestDataModel userRequestDataModel) {
        System.out.println("PUT(/api/users) request data: " + userRequestDataModel);
        Users user = userService.editUserById(userRequestDataModel);
        if (user != null)
            return ResponseEntity.status(HttpStatus.OK).body(user);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

