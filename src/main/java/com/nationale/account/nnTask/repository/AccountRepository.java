package com.nationale.account.nnTask.repository;

import com.nationale.account.nnTask.common.domain.dto.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findById(UUID accountId);
}
