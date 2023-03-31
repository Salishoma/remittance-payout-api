package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import com.oma.remittancepayoutapi.remittancepayoutapi.enums.Operation;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Source {
    private Operation operation;
    private Account sender;
    private Account recipient;

}
