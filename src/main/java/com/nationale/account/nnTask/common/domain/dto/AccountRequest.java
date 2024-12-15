package com.nationale.account.nnTask.common.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountRequest {
    @NotBlank(message = "The 'firstName' field is required")
    private String firstName;
    @NotBlank(message = "The 'lastName' field is required")
    private String lastName;
    @Positive(message = "The 'plnBalance' field must be greater than 0")
    private BigDecimal plnBalance;
}
