package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerValidateRequest {
    @NotBlank(message = "supply a public key")
    private String publickey;
    private Source source;
    private Order order;
}
