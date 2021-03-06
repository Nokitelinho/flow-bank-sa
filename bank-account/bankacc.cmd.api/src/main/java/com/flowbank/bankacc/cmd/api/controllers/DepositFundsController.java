package com.flowbank.bankacc.cmd.api.controllers;

import com.flowbank.bankacc.cmd.api.commands.DepositFundsCommand;
import com.flowbank.bankacc.core.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/depositFunds")
public class DepositFundsController {
    private final CommandGateway commandGateway;

    @Autowired
    public DepositFundsController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable(value = "id") String id,
                                                     @Valid @RequestBody DepositFundsCommand command) {
        try {
            command.setId(id);
            commandGateway.send(command);

            return new ResponseEntity<>(new BaseResponse("Funds successfully deposited!"), HttpStatus.OK);
        } catch (Exception e) {
            var safeMessage = "Error while processing request to deposit funds into bank account for id - " + id;
            log.error("IN depositFunds. {} ", safeMessage);
            return new ResponseEntity<>(new BaseResponse(safeMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
