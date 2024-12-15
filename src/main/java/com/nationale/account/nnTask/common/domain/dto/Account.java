package com.nationale.account.nnTask.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Account {
    private UUID id;
    private String firstName;
    private String lastName;
    private BigDecimal plnBalance;
    private BigDecimal usdBalance;
    private Instant creationDate;
    private Instant changeDate;
}
