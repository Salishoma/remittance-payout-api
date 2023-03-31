package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CustomerValidateRequest {
    private String publickey;
    private Source source;
    private Order order;
}
