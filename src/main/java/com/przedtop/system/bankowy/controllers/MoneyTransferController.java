package com.przedtop.system.bankowy.controllers;

import com.przedtop.system.bankowy.controllers.model.MoneyTransferRequestDataModel;
import com.przedtop.system.bankowy.services.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class MoneyTransferController {

    @Autowired
    private MoneyTransferService moneyTransferService;

    @PostMapping
    public void transferMoney(@RequestBody MoneyTransferRequestDataModel moneyTransferRequestDataModel) {
        System.out.println("received request: " + moneyTransferRequestDataModel);
        moneyTransferService.moneyTransfer(moneyTransferRequestDataModel);
    }
}
