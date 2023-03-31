package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Account {
//    @NotBlank(message = "Name cannot be empty!")
    private String name;
//    @NotBlank(message = "Address cannot be empty!")
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
