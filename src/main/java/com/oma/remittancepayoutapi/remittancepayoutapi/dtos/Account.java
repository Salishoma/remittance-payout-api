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
public class Account {
    @NotBlank(message = "Name cannot be empty!")
    private String name;
    @NotBlank(message = "Address cannot be empty!")
    private String address;
    private String mobile;
    private String country;
    private String idtype;
    private String idnumber;
    private String idexpiry;
    private String occupation;
    private String bankcode;
    private String accountnumber;
    private String beneOccupation;
    private String beneCustRelationship;
}
