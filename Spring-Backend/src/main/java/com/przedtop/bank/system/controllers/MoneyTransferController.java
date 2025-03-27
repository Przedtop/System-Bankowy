package com.przedtop.bank.system.controllers;

import com.przedtop.bank.system.model.MoneyTransferDTO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Operation(summary = "Transfer, withdraw, deposit money", description = "To deposit money set senderAccountNumber to 0, to withdraw money set amountToTransfer to negative value and senderAccountNumber to 0")
    public ResponseEntity<String> transferMoney(@RequestBody @Valid MoneyTransferDTO moneyTransferDTO, BindingResult bindingResult){
        logger.info("Executing transferMoney");

        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            logger.warn("Validation failed: {}", errorMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + errorMessages + "\n" + moneyTransferDTO.properUsage());
        }

        logger.debug("POST(/api/transfer) request data: {}", moneyTransferDTO);

        try{
            if(moneyTransferService.moneyTransfer(moneyTransferDTO)) {
                return ResponseEntity.status(HttpStatus.OK).body("Money transfer successful");
            } else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Money transfer failed");
            }
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}