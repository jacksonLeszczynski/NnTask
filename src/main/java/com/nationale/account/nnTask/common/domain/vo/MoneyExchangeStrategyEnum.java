package com.nationale.account.nnTask.common.domain.vo;

import com.nationale.account.nnTask.common.domain.exception.RateNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum MoneyExchangeStrategyEnum {
    USD("usd", (amount, rate) -> amount.divide(rate, 2, RoundingMode.HALF_UP));

    private final String type;
    public final BiFunction<BigDecimal, BigDecimal, BigDecimal> moneyExchangeFunction;

    MoneyExchangeStrategyEnum(String type, BiFunction<BigDecimal, BigDecimal, BigDecimal> moneyExchangeFunction) {
        this.type = type;
        this.moneyExchangeFunction = moneyExchangeFunction;
    }

    public static MoneyExchangeStrategyEnum fromType(String type) {
        return Arrays.stream(values())
                .filter(strategy -> strategy.type.equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new RateNotFoundException("Invalid exchange type: " + type));
    }
}
