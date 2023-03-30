package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class StatusResponse {
    private String code;
    private String message;
    private Transaction transaction;
    private Lock lock;

}
