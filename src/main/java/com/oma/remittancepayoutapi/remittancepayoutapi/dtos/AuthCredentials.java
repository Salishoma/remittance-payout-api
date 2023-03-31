package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthCredentials {
    private String access_token;
    private Long created_at;
    private Long expires_in;
}
