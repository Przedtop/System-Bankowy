package com.przedtop.bank.system;

import com.przedtop.bank.system.controllers.model.MoneyTransferRequestDataModel;
import com.przedtop.bank.system.services.MoneyTransferService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        MoneyTransferService moneyTransferService = context.getBean(MoneyTransferService.class);
        MoneyTransferRequestDataModel moneyTransferRequestDataModel = new MoneyTransferRequestDataModel();
    }
}