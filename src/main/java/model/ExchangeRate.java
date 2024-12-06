package model;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
@Data
public class ExchangeRate {
    private Long id;
    @NonNull
    private Currency baseCurrency;
    @NonNull
    private Currency targetCurrency;
    @NonNull
    private BigDecimal rate;
}
