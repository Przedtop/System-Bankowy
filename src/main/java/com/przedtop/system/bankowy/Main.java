package com.przedtop.system.bankowy;

import com.przedtop.system.bankowy.datamanage.UserData;
import com.przedtop.system.bankowy.entity.Users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        UserData userData = context.getBean(UserData.class);
        Users users = userData.getUserById(2L);
        System.out.println(users.getImie());
    }

}
