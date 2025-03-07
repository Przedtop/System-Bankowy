package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.MoneyTransferRequestDataModel;
import com.przedtop.bank.system.services.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> transferMoney(@RequestBody MoneyTransferRequestDataModel moneyTransferRequestDataModel) {
        System.out.println("POST(/api/transfer) request data: " + moneyTransferRequestDataModel);
        if (moneyTransferService.moneyTransfer(moneyTransferRequestDataModel)) {
            System.out.println("money transferred successfully");
            return ResponseEntity.status(HttpStatus.OK).body("money transferred successfully");
        } else {
            System.out.println("money transfer failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("money transfer failed");
        }
    }
}
