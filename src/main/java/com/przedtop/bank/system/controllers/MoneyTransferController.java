package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.model.MoneyTransferRequestDataModel;
import com.przedtop.bank.system.services.AccountService;
import com.przedtop.bank.system.services.MoneyTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transfer")
@Tag(name="Money Transfer", description = "Transfer, withdraw, deposit money")
public class MoneyTransferController {

    private final static Logger logger = LoggerFactory.getLogger(MoneyTransferController.class);

    private final MoneyTransferService moneyTransferService;
    private final AccountService accountService;

    public MoneyTransferController(MoneyTransferService moneyTransferService, AccountService accountService) {
        this.moneyTransferService = moneyTransferService;
        this.accountService = accountService;
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    @Operation(summary = "Transfer, withdraw, deposit money", description = "To deposit money set senderAccountNumber to 0, to withdraw money set amountToTransfer to negative value and senderAccountNumber to 0")
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

        if (accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getReceiverAccountNumber()) == null &&
                accountService.getAccountByAccountNumber(moneyTransferRequestDataModel.getSenderAccountNumber()) == null) {
            logger.error("Account not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

        if (moneyTransferService.moneyTransfer(moneyTransferRequestDataModel)) {
            logger.info("Money transferred successfully");
            return ResponseEntity.status(HttpStatus.OK).body("Money transferred successfully");
        } else {
            logger.error("Money transfer failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Money transfer failed");
        }
    }
}