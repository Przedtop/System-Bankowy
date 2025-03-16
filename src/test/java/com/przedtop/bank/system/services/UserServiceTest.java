package com.przedtop.bank.system.services;

import com.przedtop.bank.system.controllers.model.UserRequestDataModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private void createTestUser() {
        UserRequestDataModel userRequestDataModel = new UserRequestDataModel();

        userRequestDataModel.setLastName("Kowalski");
        userRequestDataModel.setName("Jan");
        userRequestDataModel.setIdentificationNumber(100L);
        userRequestDataModel.setPassword("password");
        userRequestDataModel.setLogin("login");

        userService.createUser(userRequestDataModel);
    }
    private void deleteTestUser() {
        userService.deleteUserByIdentificationNumber(100L);
    }

    @Test
    void createUser_success() {
        UserRequestDataModel userRequestDataModel = new UserRequestDataModel();

        userRequestDataModel.setLastName("Kowalski");
        userRequestDataModel.setName("Jan");
        userRequestDataModel.setIdentificationNumber(100L);
        userRequestDataModel.setPassword("password");
        userRequestDataModel.setLogin("login");

        Assertions.assertNotNull(userService.createUser(userRequestDataModel));
    }

    @Test
    void getUserById_success() {
        createTestUser();
        Assertions.assertNotNull(userService.getUserByIdentificationNumber(100L));
        deleteTestUser();
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void editUserById() {
    }
}