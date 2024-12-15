package com.nationale.account.nnTask.common.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "account")
@Entity
public class AccountEntity {
    @Id
    private UUID id;

    private String firstName;
    private String lastName;
    private BigDecimal plnBalance;
    private BigDecimal usdBalance;
    private Instant creationDate;
    private Instant changeDate;
}
