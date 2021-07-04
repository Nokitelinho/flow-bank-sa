package com.flowbank.bankacc.query.api.handlers;

import com.flowbank.bankacc.core.models.BankAccount;
import com.flowbank.bankacc.query.api.dto.AccountLookupResponse;
import com.flowbank.bankacc.query.api.dto.EqualityType;
import com.flowbank.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.flowbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.flowbank.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.flowbank.bankacc.query.api.queries.FindAllAccountsQuery;
import com.flowbank.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountQueryHandlerImpl implements AccountQueryHandler {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
        var bankAccount = accountRepository.findById(query.getId());
        return bankAccount.map(account -> new AccountLookupResponse("Bank Account Successfully Returned!", account))
                .orElseGet(() -> new AccountLookupResponse("No Bank Account Found for ID - " + query.getId()));
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query) {
        var bankAccount = accountRepository.findByAccountHolderId(query.getAccountHolderId());
        return bankAccount.map(account -> new AccountLookupResponse("Bank Account Successfully Returned!", account))
                .orElseGet(() -> new AccountLookupResponse("No Bank Account Found for Holder ID - " + query.getAccountHolderId()));
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAllAccounts(FindAllAccountsQuery query) {
        var bankAccountIterator = accountRepository.findAll();

        if (!bankAccountIterator.iterator().hasNext())
            return new AccountLookupResponse("No Bank Accounts were Found!");

        var bankAccounts = new ArrayList<BankAccount>();
        bankAccountIterator.forEach(bankAccounts::add);
        var count = bankAccounts.size();

        return new AccountLookupResponse("Successfully Returned " + count + " Bank Account(s)!", bankAccounts);
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query) {
        var bankAccounts = query.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(query.getBalance())
                : accountRepository.findByBalanceLessThan(query.getBalance());

        var count = bankAccounts.size();

        return count > 0
                ? new AccountLookupResponse("Successfully returned " + count + " Bank Account(s)!", bankAccounts)
                : new AccountLookupResponse("No Bank Accounts Were Found!");
    }
}
