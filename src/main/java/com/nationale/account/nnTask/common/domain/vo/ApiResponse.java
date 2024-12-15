package com.nationale.account.nnTask.common.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nationale.account.nnTask.common.domain.dto.Account;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResponse implements Response<Account> {
    private Account responseObject;
    private ResponseCodeStatus responseStatus;
    private List<String> errorMessages;
}
