package com.nationale.account.nnTask.service;

import com.nationale.account.nnTask.common.domain.dto.Account;
import com.nationale.account.nnTask.common.domain.exception.AccountNotFoundException;
import com.nationale.account.nnTask.common.domain.vo.MoneyExchangeStrategyEnum;
import com.nationale.account.nnTask.common.domain.vo.RestClientService;
import com.nationale.account.nnTask.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final RestClientService restClientService;

    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.restClientService = new CurrencyRestClientService();
    }

    @Transactional
    public Account createAccount(String firstName, String lastName, BigDecimal plnBalance) {
        Account account = Account.builder()
                .firstName(firstName)
                .lastName(lastName)
                .plnBalance(plnBalance)
                .creationDate(Instant.now())
                .build();
        return accountRepository.save(account);
    }

    public Account getAccount(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Could not find account with accountId:" + accountId));
    }

    public Account exchangeAccountCurrency(UUID id, String type) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account for exchange money not found"));
        BigDecimal rate = restClientService.getDataFromApi(type);
        MoneyExchangeStrategyEnum strategy = MoneyExchangeStrategyEnum.fromType(type);
        BigDecimal exchangedAmount = strategy.moneyExchangeFunction.apply(account.getPlnBalance(), rate);
        account.setChangeDate(Instant.now());

        switch (strategy) {
            case USD -> account.setUsdBalance(exchangedAmount);
            default -> throw new IllegalStateException("Unexpected value: " + strategy);
        }
        return accountRepository.save(account);
    }

}
