package com.przedtop.bank.system.services;

import com.przedtop.bank.system.model.UserRequestDataModel;
import com.przedtop.bank.system.entity.Users;
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
        deleteTestUsers();
        UserRequestDataModel userRequestDataModel = new UserRequestDataModel();

        userRequestDataModel.setLastName("Kowalski");
        userRequestDataModel.setName("Jan");
        userRequestDataModel.setIdentificationNumber(100L);
        userRequestDataModel.setPassword("password");
        userRequestDataModel.setLogin("login");

        userService.createUser(userRequestDataModel);
    }

    private void deleteTestUsers() {
        if (userService.getUserByIdentificationNumber(100L) != null) userService.deleteUserByIdentificationNumber(100L);
        if (userService.getUserByIdentificationNumber(101L) != null) userService.deleteUserByIdentificationNumber(101L);
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
    void createUser_userAlreadyExists() {
        createTestUser();
        UserRequestDataModel userRequestDataModel = new UserRequestDataModel();

        userRequestDataModel.setLastName("Kowalski");
        userRequestDataModel.setName("Jan");
        userRequestDataModel.setIdentificationNumber(100L);
        userRequestDataModel.setPassword("password");
        userRequestDataModel.setLogin("login");

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createUser(userRequestDataModel));

        deleteTestUsers();
    }

    @Test
    void getUserByIdentificationNumber_success() {
        createTestUser();
        Assertions.assertNotNull(userService.getUserByIdentificationNumber(100L));
        deleteTestUsers();
    }

    @Test
    void getUserByIdentificationNumber_userNotFound() {
        Assertions.assertNull(userService.getUserByIdentificationNumber(100L));
    }

    @Test
    void getUserByIdentificationNumber_Null() {
        Assertions.assertNull(userService.getUserById(null));
    }

    @Test
    void getUserById_success() {
        createTestUser();
        Users user = userService.getUserByIdentificationNumber(100L);
        Assertions.assertNotNull(userService.getUserById(user.getId()));
        deleteTestUsers();
    }

    @Test
    void getUserById_userNotFound() {
        Assertions.assertNull(userService.getUserById(123L));
    }

    @Test
    void getUserById_null() {
        Assertions.assertNull(userService.getUserById(null));
    }


    @Test
    void deleteUserById_success() {
        createTestUser();
        Users user = userService.getUserByIdentificationNumber(100L);
        Assertions.assertTrue(userService.deleteUserById(user.getId()));
    }

    @Test
    void deleteUserById_userNotFound() {
        Assertions.assertFalse(userService.deleteUserById(100L));
    }

    @Test
    void deleteUserById_null() {
        Assertions.assertFalse(userService.deleteUserById(null));
    }

    @Test
    void deleteUserByIdentificationNumber_success() {
        createTestUser();
        Assertions.assertTrue(userService.deleteUserByIdentificationNumber(100L));
    }

    @Test
    void deleteUserByIdentificationNumber_userNotFound() {
        Assertions.assertFalse(userService.deleteUserByIdentificationNumber(100L));
    }

    @Test
    void deleteUserByIdentificationNumber_null() {
        Assertions.assertFalse(userService.deleteUserByIdentificationNumber(null));
    }

    @Test
    void editUserById_success() {
        createTestUser();
        Users user = userService.getUserByIdentificationNumber(100L);
        UserRequestDataModel userRequestDataModel = new UserRequestDataModel();
        userRequestDataModel.setId(user.getId());
        userRequestDataModel.setLastName("Kowalski");
        userRequestDataModel.setName("Jan");
        userRequestDataModel.setIdentificationNumber(101L);
        userRequestDataModel.setPassword("password");
        userRequestDataModel.setLogin("login");
        Users user2 = userService.editUserById(userRequestDataModel);

        Assertions.assertEquals(userService.getUserByIdentificationNumber(101L).getId(), user2.getId());
        userService.deleteUserByIdentificationNumber(101L);
    }

    @Test
    void editUserById_fail() {
        createTestUser();
        Users user = userService.getUserByIdentificationNumber(100L);
        UserRequestDataModel userRequestDataModel = new UserRequestDataModel();

        userRequestDataModel.setId(user.getId());
        userRequestDataModel.setLastName(null);
        userRequestDataModel.setName(null);
        userRequestDataModel.setIdentificationNumber(null);
        userRequestDataModel.setPassword(null);
        userRequestDataModel.setLogin(null);

        Assertions.assertThrows(NullPointerException.class, () -> userService.editUserById(userRequestDataModel));
        userService.deleteUserByIdentificationNumber(100L);
    }
}