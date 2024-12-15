package com.nationale.account.nnTask.common.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ExchangeRequest {
    @NotBlank(message = "The 'type' field is required")
    private String currency;
}
