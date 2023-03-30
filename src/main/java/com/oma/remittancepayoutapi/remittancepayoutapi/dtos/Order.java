package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import com.oma.remittancepayoutapi.remittancepayoutapi.enums.Currency;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {
    @NotNull(message = "amount is required!")
    private BigDecimal amount;
    private String description;
    private String reason;
    @NotNull(message = "currency is required!")
    private Currency currency;
    @NotEmpty(message = "country is required!")
    private String country;
    private String secretquestion;
    private String secretanswer;
}
