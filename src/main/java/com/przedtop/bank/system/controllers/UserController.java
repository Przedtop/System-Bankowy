package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.UserRequestDataModel;
import com.przedtop.bank.system.entity.Users;
import com.przedtop.bank.system.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Manage users in the system")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    @Operation(summary = "Create user")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequestDataModel userRequestDataModel, BindingResult bindingResult) {
        logger.info("Executing createUser");

        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            logger.error("Validation failed: {}", errorMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + errorMessages + "\n" + userRequestDataModel.properUsage());
        }

        logger.debug("POST(/api/users) request data: {}", userRequestDataModel);
        Users user = userService.createUser(userRequestDataModel);
        if (user != null) {
            logger.info("User created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } else {
            logger.error("Failed to create user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    @Operation(summary = "Get user by Id", description = "Returns a user by their Id")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        logger.info("Executing getUserById");
        logger.debug("GET(/api/users) request data: {}", userService.getUserById(id));
        Users user = userService.getUserById(id);
        if (user != null) {
            logger.info("User found successfully");
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            logger.error("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    @Operation(summary = "Delete user by Id")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        logger.info("Executing deleteUserById");
        logger.debug("DELETE(/api/users) request data: {}", userService.getUserById(id));
        if (userService.deleteUserById(id)) {
            logger.info("Deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            logger.error("Delete failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");
        }
    }

    @PutMapping
    @CrossOrigin(origins = "*")
    @Operation(summary = "Update user")
    public ResponseEntity<Users> updateUser(@RequestBody UserRequestDataModel userRequestDataModel) {
        logger.info("Executing updateUser");
        logger.debug("PUT(/api/users) request data: {}", userRequestDataModel);
        if(userRequestDataModel.getId() == null) {
            logger.error("Update failed because id is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Users user = userService.editUserById(userRequestDataModel);
        if (user != null) {
            logger.info("User updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            logger.error("User not found update failed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}