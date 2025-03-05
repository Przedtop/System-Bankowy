package com.przedtop.system.bankowy;

import com.przedtop.system.bankowy.services.UserService;
import com.przedtop.system.bankowy.entity.Users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        UserService userService = context.getBean(UserService.class);
        Users users = userService.getUserById(2L);
    }

}
