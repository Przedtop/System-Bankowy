package com.przedtop.bank.system.services;

import com.przedtop.bank.system.model.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    public void createTestUser() {
        deleteTestUsers();
        UserDTO userDTO = new UserDTO();

        userDTO.setLastName("Kowalski");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(100L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");
        userDTO.setRole("USER");

        userService.createUser(userDTO);
    }
    @AfterEach
    public void deleteTestUsers() {
        if (userService.getUserByIdentificationNumber(100L) != null) userService.deleteUserByIdentificationNumber(100L);
        if (userService.getUserByIdentificationNumber(101L) != null) userService.deleteUserByIdentificationNumber(101L);
    }


    @Test
    public void testCreateUserSuccess() {
        UserDTO userDTO = new UserDTO();
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password");
        userDTO.setLogin("login12");
        userDTO.setRole("USER");

        userService.createUser(userDTO);

        assertEquals("Nowak", userService.getUserByIdentificationNumber(101L).getLastName());
    }
    @Test
    public void testCreateUserSuccessNullRole() {
        UserDTO userDTO = new UserDTO();
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password");
        userDTO.setLogin("login12");
        userDTO.setRole(null);

        userService.createUser(userDTO);

        assertEquals("Nowak", userService.getUserByIdentificationNumber(101L).getLastName());
    }
    @Test
    public void testCreateUserUserIdentificationNumberAlreadyExist() {
        UserDTO userDTO = new UserDTO();
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(100L);
        userDTO.setPassword("password");
        userDTO.setLogin("login12");
        userDTO.setRole("USER");

        try {
            userService.createUser(userDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("User with this identification number already exists", e.getMessage());
        }
    }
    @Test
    public void testCreateUserLoginAlreadyExist() {
        UserDTO userDTO = new UserDTO();
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");
        userDTO.setRole("USER");

        try {
            userService.createUser(userDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("User with this login already exists", e.getMessage());
        }
    }
    @Test
    public void testCreateUserNull() {
        UserDTO userDTO = null;

        assertNull(userService.createUser(userDTO));
    }
    @Test
    public void testCreateUserIdentificationNumberZero() {
        UserDTO userDTO = new UserDTO();
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(0L);
        userDTO.setPassword("password");
        userDTO.setLogin("login12");
        userDTO.setRole("USER");

        assertNull(userService.createUser(userDTO));
    }

    @Test
    public void testGetUserByIdentificationNumberSuccess() {
        assertEquals("Kowalski", userService.getUserByIdentificationNumber(100L).getLastName());
    }
    @Test
    public void testGetUserByIdentificationNumberNull() {
        assertNull(userService.getUserByIdentificationNumber(null));
    }
    @Test
    public void testGetUserByIdentificationNumberZero() {
        assertNull(userService.getUserByIdentificationNumber(0L));
    }
    @Test
    public void testGetUserByIdentificationNumberNotFound() {
        assertNull(userService.getUserByIdentificationNumber(101L));
    }

    @Test
    public void testDeleteUserByIdentificationNumberSuccess() {
        userService.deleteUserByIdentificationNumber(100L);

        assertNull(userService.getUserByIdentificationNumber(100L));
    }
    @Test
    public void testDeleteUserByIdentificationNumberNull() {
        assertFalse(userService.deleteUserByIdentificationNumber(null));
    }
    @Test
    public void testDeleteUserByIdentificationNumberNotFound() {
        assertFalse(userService.deleteUserByIdentificationNumber(101L));
    }

    @Test
    public void testGetUserByIdSuccess() {
        assertNotNull(userService.getUserById(userService.getUserByIdentificationNumber(100L).getId()));
    }
    @Test
    public void testGetUserByIdNull() {
        assertNull(userService.getUserById(null));
    }
    @Test
    public void testGetUserByIdNotFound() {
        assertNull(userService.getUserById(101L));
    }

    @Test
    public void testDeleteUserByIdSuccess() {
        userService.deleteUserById(userService.getUserByIdentificationNumber(100L).getId());
        assertNull(userService.getUserByIdentificationNumber(100L));
    }
    @Test
    public void testDeleteUserByIdNull() {
        assertFalse(userService.deleteUserById(null));
    }
    @Test
    public void testDeleteUserByIdNotFound() {
        assertFalse(userService.deleteUserById(101L));
    }

    @Test
    public void testEditUserByIdSuccessStage1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(100L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");
        userDTO.setRole("USER");

        userService.editUserById(userDTO);

        assertEquals("Nowak", userService.getUserByIdentificationNumber(100L).getLastName());
    }
    @Test
    public void testEditUserByIdSuccessStage2() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Damian");
        userDTO.setIdentificationNumber(100L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");
        userDTO.setRole("USER");

        userService.editUserById(userDTO);

        assertEquals("Damian", userService.getUserByIdentificationNumber(100L).getName());
    }
    @Test
    public void testEditUserByIdSuccessStage3() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Damian");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");
        userDTO.setRole("USER");

        userService.editUserById(userDTO);

        assertEquals(101, userService.getUserByIdentificationNumber(101L).getIdentificationNumber());
    }
    @Test
    public void testEditUserByIdSuccessStage4() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Damian");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password123");
        userDTO.setLogin("login12");
        userDTO.setRole("USER");

        userService.editUserById(userDTO);

        assertEquals("password123", userService.getUserByIdentificationNumber(101L).getPassword());
    }
    @Test
    public void testEditUserByIdSuccessStage5() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Damian");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password123");
        userDTO.setLogin("login123");
        userDTO.setRole("USER");

        userService.editUserById(userDTO);

        assertEquals("login123", userService.getUserByIdentificationNumber(101L).getLogin());
    }
    @Test
    public void testEditUserByIdSuccessStage6() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Damian");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password123");
        userDTO.setLogin("login123");
        userDTO.setRole("ADMIN");

        userService.editUserById(userDTO);

        assertEquals("ADMIN", userService.getUserByIdentificationNumber(101L).getRole());
    }
    @Test
    public void testEditUserByIdNotFound() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(101L);
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");

        assertNull(userService.editUserById(userDTO));
    }
    @Test
    public void testEditUserByIdNull() {
        UserDTO userDTO = null;

        assertNull(userService.editUserById(userDTO));
    }
    @Test
    public void testEditUserByIdIdentificationNumberZero() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(0L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");

        assertEquals(100, userService.editUserById(userDTO).getIdentificationNumber());
    }
    @Test
    public void testEditUserByIdIdentificationNumberAlreadyExist() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(100L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");

        try {
            userService.editUserById(userDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("User with this identification number already exists", e.getMessage());
        }
    }
    @Test
    public void testEditUserByIdLoginAlreadyExist() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");

        try {
            userService.editUserById(userDTO);
        } catch (IllegalArgumentException e) {
            assertEquals("User with this login already exists", e.getMessage());
        }
    }
    @Test
    public void testEditUserByIdNullRole() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");
        userDTO.setRole(null);

        userService.editUserById(userDTO);

        assertEquals("USER", userService.getUserByIdentificationNumber(101L).getRole());
    }
    @Test
    public void testEditUserByIdNullPassword() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword(null);
        userDTO.setLogin("login");
        userDTO.setRole("USER");

        userService.editUserById(userDTO);

        assertEquals("password",userService.getUserByIdentificationNumber(101L).getPassword());
    }
    @Test
    public void testEditUserByIdNullLogin() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password");
        userDTO.setLogin(null);
        userDTO.setRole("USER");

        userService.editUserById(userDTO);

        assertEquals("login",userService.getUserByIdentificationNumber(101L).getLogin());
    }
    @Test
    public void testEditUserByIdNullName() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName("Nowak");
        userDTO.setName(null);
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");
        userDTO.setRole("USER");

        userService.editUserById(userDTO);

        assertEquals("Jan",userService.getUserByIdentificationNumber(101L).getName());
    }
    @Test
    public void testEditUserByIdNullLastName() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userService.getUserByIdentificationNumber(100L).getId());
        userDTO.setLastName(null);
        userDTO.setName("Jan");
        userDTO.setIdentificationNumber(101L);
        userDTO.setPassword("password");
        userDTO.setLogin("login");
        userDTO.setRole("USER");

        userService.editUserById(userDTO);

        assertEquals("Kowalski",userService.getUserByIdentificationNumber(101L).getLastName());
    }

    @Test
    public void testLoadUserByUsernameSuccess() {
        assertNotNull(userService.loadUserByUsername("login"));
    }
    @Test
    public void testLoadUserByUsernameNotFound() {
        try{
            userService.loadUserByUsername("login123");
        } catch (UsernameNotFoundException e) {
            assertEquals("User not found", e.getMessage());
        }
    }
}