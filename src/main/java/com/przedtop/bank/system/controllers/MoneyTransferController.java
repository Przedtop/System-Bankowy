package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.MoneyTransferRequestDataModel;
import com.przedtop.bank.system.services.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfer")
public class MoneyTransferController {

    @Autowired
    private MoneyTransferService moneyTransferService;

    @PostMapping
    public String transferMoney(@RequestBody MoneyTransferRequestDataModel moneyTransferRequestDataModel) {
        System.out.println("POST(/api/transfer) request data: " + moneyTransferRequestDataModel);
        if (moneyTransferService.moneyTransfer(moneyTransferRequestDataModel)) {
            System.out.println("money transferred successfully");
            return "money transferred successfully";
        }
        else {
            System.out.println("money transfer failed");
            return "money transfer failed";
        }
    }
}
