package com.przedtop.system.bankowy;

import com.przedtop.system.bankowy.datamanage.UserData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        UserData userData = new UserData();
    }

}
