package com.nationale.account.nnTask.common.domain.mapper;

import com.nationale.account.nnTask.common.domain.dto.Account;
import com.nationale.account.nnTask.common.domain.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountEntity accountToAccountEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .plnBalance(account.getPlnBalance())
                .usdBalance(account.getUsdBalance())
                .creationDate(account.getCreationDate())
                .changeDate(account.getChangeDate())
                .build();
    }

    public Account accountEntityToAccount(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .firstName(accountEntity.getFirstName())
                .lastName(accountEntity.getLastName())
                .plnBalance(accountEntity.getPlnBalance())
                .usdBalance(accountEntity.getUsdBalance())
                .creationDate(accountEntity.getCreationDate())
                .changeDate(accountEntity.getChangeDate())
                .build();
    }

}
