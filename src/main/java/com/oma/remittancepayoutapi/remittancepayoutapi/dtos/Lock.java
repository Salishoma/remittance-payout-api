package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Lock {
    private String status;
    private String by;
    private long time;
    private Order order;
    private Source source;
}
