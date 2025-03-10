package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.MoneyTransferRequestDataModel;
import com.przedtop.bank.system.services.MoneyTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfer")
public class MoneyTransferController {

    private final static Logger logger = LoggerFactory.getLogger(MoneyTransferController.class);

    private final MoneyTransferService moneyTransferService;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping
    public ResponseEntity<String> transferMoney(@RequestBody MoneyTransferRequestDataModel moneyTransferRequestDataModel) {
        logger.info("Executing transferMoney");
        logger.debug("POST(/api/transfer) request data: {}", moneyTransferRequestDataModel);
        if (moneyTransferService.moneyTransfer(moneyTransferRequestDataModel)) {
            logger.info("money transferred successfully");
            return ResponseEntity.status(HttpStatus.OK).body("money transferred successfully");
        } else {
            logger.warn("money transfer failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("money transfer failed");
        }
    }
}
