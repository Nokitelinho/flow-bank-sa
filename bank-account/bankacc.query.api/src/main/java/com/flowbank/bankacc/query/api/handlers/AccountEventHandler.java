package com.flowbank.bankacc.query.api.handlers;

import com.flowbank.bankacc.core.events.AccountClosedEvent;
import com.flowbank.bankacc.core.events.AccountOpenedEvent;
import com.flowbank.bankacc.core.events.FundsDepositedEvent;
import com.flowbank.bankacc.core.events.FundsWithdrawnEvent;

public interface AccountEventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
