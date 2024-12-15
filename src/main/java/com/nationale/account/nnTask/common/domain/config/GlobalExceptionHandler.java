package com.nationale.account.nnTask.common.domain.config;

import com.nationale.account.nnTask.common.domain.exception.AccountNotFoundException;
import com.nationale.account.nnTask.common.domain.exception.RateNotFoundException;
import com.nationale.account.nnTask.common.domain.vo.ApiResponse;
import com.nationale.account.nnTask.common.domain.vo.ResponseCodeStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ApiResponse handleAccountNotFoundException(AccountNotFoundException ex) {
        log.warn("Exception with app was thrown: {}", ex.getMessage());

        ApiResponse response = ApiResponse.builder()
                .responseStatus(ResponseCodeStatus.APP_ERROR)
                .errorMessages(List.of(ex.getMessage()))
                .build();

        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RateNotFoundException.class)
    public ApiResponse handleRateNotFoundException(RateNotFoundException ex) {
        log.warn("Exception with external app with currency was thrown: {}", ex.getMessage());

        ApiResponse response = ApiResponse.builder()
                .responseStatus(ResponseCodeStatus.APP_ERROR)
                .errorMessages(List.of(ex.getMessage()))
                .build();

        return response;
    }

}
