package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CustomerValidateResponse {
    private String code;
    private String message;
    private String cutomername;

}
