package com.oma.remittancepayoutapi.remittancepayoutapi.services;

import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.*;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<CustomerValidateResponse> validateCustomer(CustomerValidateRequest customerValidateRequest);

}
