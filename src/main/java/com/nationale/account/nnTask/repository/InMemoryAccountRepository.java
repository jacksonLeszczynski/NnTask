package com.nationale.account.nnTask.repository;

import com.nationale.account.nnTask.common.domain.dto.Account;
import com.nationale.account.nnTask.common.domain.entity.AccountEntity;
import com.nationale.account.nnTask.common.domain.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository()
@Profile("test")
public class InMemoryAccountRepository implements AccountRepository{

    private final Map<UUID, AccountEntity> inMemoryStore = new HashMap<>();
    private final AccountMapper accountMapper;

    @Autowired
    public InMemoryAccountRepository(final AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Account save(Account account) {
        account.setId(UUID.randomUUID());
        inMemoryStore.put(account.getId(), accountMapper.accountToAccountEntity(account));
        return account;
    }

    @Override
    public Optional<Account> findById(UUID accountId) {
        return inMemoryStore.values()
                .stream()
                .filter(account -> accountId.equals(account.getId()))
                .findFirst()
                .map(accountMapper::accountEntityToAccount);
    }
}
