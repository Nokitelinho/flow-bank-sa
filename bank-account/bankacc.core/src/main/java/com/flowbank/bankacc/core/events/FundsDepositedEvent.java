package com.flowbank.bankacc.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FundsDepositedEvent {
    private String id;
    private double amount;
    private double balance;
}
