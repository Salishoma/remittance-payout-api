package com.oma.remittancepayoutapi.remittancepayoutapi.controllers;

import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.CustomerValidateRequest;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.CustomerValidateResponse;
import com.oma.remittancepayoutapi.remittancepayoutapi.services.CustomerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@Slf4j
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request was successful"),
        @ApiResponse(responseCode = "400", description = "This is a bad request, please follow the API documentation for the proper request format."),
        @ApiResponse(responseCode = "401", description = "You do not have the credentials to access this resource."),
        @ApiResponse(responseCode = "404", description = "The resource is not available."),
        @ApiResponse(responseCode = "415", description = "The MediaType is unsupported."),
        @ApiResponse(responseCode = "500", description = "The server is down, please make sure that the Application is running")
})
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "validate")
    public CustomerValidateResponse validateCustomer(@RequestBody CustomerValidateRequest customerValidateRequest
    ) {
        ResponseEntity<CustomerValidateResponse> customerValidateResponseResponseEntity = customerService
                .validateCustomer(customerValidateRequest);
        return customerValidateResponseResponseEntity.getBody();
    }
}
