package com.przedtop.bank.system;

import com.przedtop.bank.system.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        UserService userService = context.getBean(UserService.class);
    }
}