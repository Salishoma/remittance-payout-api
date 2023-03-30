package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {
    private String publickey;
    private Transaction transaction;
    private Order order;
    private Source source;
}
