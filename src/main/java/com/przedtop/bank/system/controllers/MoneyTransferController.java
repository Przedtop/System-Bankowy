package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.controllers.model.MoneyTransferRequestDataModel;
import com.przedtop.bank.system.services.MoneyTransferService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transfer")
public class MoneyTransferController {

    private final static Logger logger = LoggerFactory.getLogger(MoneyTransferController.class);

    private final MoneyTransferService moneyTransferService;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping
    public ResponseEntity<String> transferMoney(@RequestBody @Valid MoneyTransferRequestDataModel moneyTransferRequestDataModel, BindingResult bindingResult) {
        logger.info("Executing transferMoney");

        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            logger.warn("Validation failed: {}", errorMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + errorMessages + "\n" + moneyTransferRequestDataModel.properUsage());
        }

        logger.debug("POST(/api/transfer) request data: {}", moneyTransferRequestDataModel);

        if (moneyTransferService.moneyTransfer(moneyTransferRequestDataModel)) {
            logger.info("Money transferred successfully");
            return ResponseEntity.status(HttpStatus.OK).body("Money transferred successfully");
        } else {
            logger.warn("Money transfer failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Money transfer failed");
        }
    }
}
