package com.nationale.account.nnTask.controller;


import com.nationale.account.nnTask.common.domain.dto.Account;
import com.nationale.account.nnTask.common.domain.dto.AccountRequest;
import com.nationale.account.nnTask.common.domain.dto.ExchangeRequest;
import com.nationale.account.nnTask.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/{version}/accounts")
public class AccountController extends BaseApiController {
    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected String getVersion() {
        return "v1";
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @Valid AccountRequest accountRequest) {
        Account account = accountService.createAccount(accountRequest.getFirstName(), accountRequest.getLastName(), accountRequest.getPlnBalance());
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable UUID id) {
        Account account = accountService.getAccount(id);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}/exchange")
    public ResponseEntity<Account> exchangeCurrency(@PathVariable UUID id, @RequestBody ExchangeRequest exchangeRequest) throws IOException {
        Account account = accountService.exchangeAccountCurrency(id, exchangeRequest.getCurrency());
        return ResponseEntity.ok(account);
    }
}
