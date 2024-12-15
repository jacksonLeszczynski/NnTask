package com.nationale.account.nnTask.repository;

import com.nationale.account.nnTask.common.domain.dto.Account;
import com.nationale.account.nnTask.common.domain.entity.AccountEntity;
import com.nationale.account.nnTask.common.domain.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository()
@Profile("prod")
public class DbAccountRepository implements AccountRepository {

    private final AccountRepositoryJpa accountRepositoryJpa;
    private final AccountMapper accountMapper;

    @Autowired
    public DbAccountRepository(AccountRepositoryJpa accountRepositoryJpa, AccountMapper accountMapper) {
        this.accountRepositoryJpa = accountRepositoryJpa;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account save(Account account) {
        return accountMapper.accountEntityToAccount(accountRepositoryJpa.save(accountMapper.accountToAccountEntity(account)));
    }

    @Override
    public Optional<Account> findById(UUID accountId) {
        return accountRepositoryJpa.findById(accountId)
                .map(accountMapper::accountEntityToAccount);
    }
}

@Repository
interface AccountRepositoryJpa extends CrudRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findById(UUID accountId);
}
