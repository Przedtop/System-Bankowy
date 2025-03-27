package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.model.UserDTO;
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
    @Operation(summary = "Create user")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        logger.info("Executing createUser");
        logger.debug("POST(/api/users) request data: {}", userDTO);

        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            logger.error("Validation failed: {}", errorMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + errorMessages + "\n" + userDTO.properUsage());
        }
        try {
            Users user = userService.createUser(userDTO);
            if (user != null) {
                logger.info("User created successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(user);
            } else {
                logger.error("Failed to create user");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
            }
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by Id", description = "Returns a user by their Id")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        logger.info("Executing getUserById");
        logger.debug("GET(/api/users) request data: {}", userService.getUserById(id));
        Users user = userService.getUserById(id);
        if (user != null) {
            logger.info("User found successfully id: {}", id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            logger.error("User not found id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/idNumber/{identificationNumber}")
    @Operation(summary = "Get user by identification number", description = "Returns a user by their identification number")
    public ResponseEntity<?> getUserByIdentificationNumber(@PathVariable Long identificationNumber) {
        logger.info("Executing getUserByIdentificationNumber");
        logger.debug("GET(/api/users/identification) request data: {}", userService.getUserByIdentificationNumber(identificationNumber));
        Users user = userService.getUserByIdentificationNumber(identificationNumber);
        if (user != null) {
            logger.info("User found successfully identificationNumber: {}", identificationNumber);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            logger.error("User not found identificationNumber: {}", identificationNumber);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by Id")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        logger.info("Executing deleteUserById");
        logger.debug("DELETE(/api/users) request data: {}", userService.getUserById(id));
        if (userService.deleteUserById(id)) {
            logger.info("Deleted successfully id: {}", id);
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            logger.error("Delete failed id: {}", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");
        }
    }

    @DeleteMapping("/idNumber/{identificationNumber}")
    @Operation(summary = "Delete user by identification number")
    public ResponseEntity<?> deleteUserByIdentificationNumber(@PathVariable Long identificationNumber) {
        logger.info("Executing deleteUserByIdentificationNumber");
        logger.debug("DELETE(/api/users/idNumber) request data: {}", userService.getUserByIdentificationNumber(identificationNumber));
        if (userService.deleteUserByIdentificationNumber(identificationNumber)) {
            logger.info("Deleted successfully identificationNumber: {}", identificationNumber);
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            logger.error("Delete failed identificationNumber: {}", identificationNumber);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");
        }
    }

    @PutMapping
    @Operation(summary = "Update user")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        logger.info("Executing updateUser");
        logger.debug("PUT(/api/users) request data: {}", userDTO);
        if (userDTO.getId() == null) {
            logger.error("Update failed because id is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed because id is null");
        }

        Users user = userService.editUserById(userDTO);
        if (user != null) {
            logger.info("User updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            logger.error("User not found update failed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found update failed");
        }
    }
}