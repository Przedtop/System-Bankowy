package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.UserRequestDataModel;
import com.przedtop.bank.system.entity.Users;
import com.przedtop.bank.system.logs.WriteLog;
import com.przedtop.bank.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequestDataModel userRequestDataModel) {
        System.out.println("POST(/api/users) request data: " + userRequestDataModel);
        WriteLog.writeLog(userRequestDataModel.toString(), Optional.of("POST(/api/users) request data: "));
        Users user = userService.createUser(userRequestDataModel);
        if (user != null) {
            WriteLog.writeLogAndPrintMessage("User created successfully", Optional.empty());
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } else {
            WriteLog.writeLogAndPrintMessage("Failed to create user", Optional.empty());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        System.out.println("GET(/api/users) request data: " + userService.getUserById(id));
        if (userService.getUserById(id) != null)
            WriteLog.writeLog(userService.getUserById(id).toString(), Optional.of("DELETE(/api/users) request data: "));
        else WriteLog.writeLog(null, Optional.of("DELETE(/api/users) request data: "));
        Users user = userService.getUserById(id);
        if (user != null) {
            WriteLog.writeLogAndPrintMessage("User found successfully", Optional.empty());
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            WriteLog.writeLogAndPrintMessage("User not found", Optional.empty());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        System.out.println("DELETE(/api/users) request data: " + userService.getUserById(id));
        if (userService.getUserById(id) != null)
            WriteLog.writeLog(userService.getUserById(id).toString(), Optional.of("DELETE(/api/users) request data: "));
        else WriteLog.writeLog(null, Optional.of("DELETE(/api/users) request data: "));
        if (userService.deleteUserById(id)) {
            WriteLog.writeLogAndPrintMessage("Deleted succesfuly", Optional.empty());
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            WriteLog.writeLogAndPrintMessage("Delete failed", Optional.empty());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");
        }
    }

    @PutMapping
    public ResponseEntity<Users> updateUser(@RequestBody UserRequestDataModel userRequestDataModel) {
        System.out.println("PUT(/api/users) request data: " + userRequestDataModel);
        WriteLog.writeLog(userRequestDataModel.toString(), Optional.of("PUT(/api/users) request data: "));
        Users user = userService.editUserById(userRequestDataModel);
        if (user != null) {
            WriteLog.writeLogAndPrintMessage("User updated successfully", Optional.empty());
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            WriteLog.writeLogAndPrintMessage("User not found", Optional.empty());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

