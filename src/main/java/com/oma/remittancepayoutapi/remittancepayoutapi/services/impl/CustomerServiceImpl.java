package com.oma.remittancepayoutapi.remittancepayoutapi.services.impl;

import com.oma.remittancepayoutapi.remittancepayoutapi.auth.AuthenticationService;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.*;
import com.oma.remittancepayoutapi.remittancepayoutapi.exceptions.ApiBadRequestException;
import com.oma.remittancepayoutapi.remittancepayoutapi.exceptions.ApiResourceNotFoundException;
import com.oma.remittancepayoutapi.remittancepayoutapi.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Value("${seerbit.baseUrl}")
    private String BASE_URL;

    private final AuthenticationService authentication;
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<CustomerValidateResponse> validateCustomer(CustomerValidateRequest customerValidateRequest) {

        URI uri = getURI();
        HttpHeaders httpHeaders = getHeaders();
        HttpEntity<CustomerValidateRequest> httpEntity = new HttpEntity<>(customerValidateRequest, httpHeaders);
        ResponseEntity<CustomerValidateResponse> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, CustomerValidateResponse.class);

        if (exchange.getBody() == null) {
            throw new ApiResourceNotFoundException("Resource not found");
        }

        if (!"Successful".equals(exchange.getBody().getMessage())) {
            throw new ApiBadRequestException(exchange.getBody().getMessage());
        }
        return exchange;
    }

    private HttpHeaders getHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String validToken = authentication.getValidToken();
        httpHeaders.add("Authorization", "Bearer " + validToken);
        return httpHeaders;

    }
    private URI getURI() {
        URI uri;
        try {
            uri = new URI(BASE_URL.concat("/customer/validate"));
        } catch (URISyntaxException exception) {
            throw new RuntimeException(exception);
        }
        return uri;
    }
}
