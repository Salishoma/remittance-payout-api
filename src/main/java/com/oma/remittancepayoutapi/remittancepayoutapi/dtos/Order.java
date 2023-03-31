package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import com.oma.remittancepayoutapi.remittancepayoutapi.enums.Currency;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Order {
    private BigDecimal amount;
    private String description;
    private String reason;
    private Currency currency;
    private String country;
    private String secretquestion;
    private String secretanswer;
}
