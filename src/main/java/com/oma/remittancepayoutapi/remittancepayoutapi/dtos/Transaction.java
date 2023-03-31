package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    @NotBlank(message = "reference cannot be empty!")
    private String reference;
    private String linkingreference;
}
