package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

import com.oma.remittancepayoutapi.remittancepayoutapi.enums.Operation;
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
public class Source {
    private Operation operation;
    private Account sender;
    private Account recipient;

}
