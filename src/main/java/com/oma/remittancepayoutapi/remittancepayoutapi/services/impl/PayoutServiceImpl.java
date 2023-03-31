package com.oma.remittancepayoutapi.remittancepayoutapi.services.impl;

import com.oma.remittancepayoutapi.remittancepayoutapi.auth.AuthenticationService;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.RequestDto;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.Response;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.StatusResponse;
import com.oma.remittancepayoutapi.remittancepayoutapi.exceptions.ApiBadRequestException;
import com.oma.remittancepayoutapi.remittancepayoutapi.exceptions.ApiResourceNotFoundException;
import com.oma.remittancepayoutapi.remittancepayoutapi.services.PayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class PayoutServiceImpl implements PayoutService {

    @Value("${seerbit.baseUrl}")
    private String BASE_URL;

    private final AuthenticationService authentication;
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<Response> payout(RequestDto body) {
        URI uri = getURI("/account/payout");
        return getResponseResponseEntity(body, uri, "successful");
    }

    @Override
    public ResponseEntity<StatusResponse> status(String reference) {
        URI uri = getURI("/payout/status?reference=" + reference);
        HttpHeaders httpHeaders = getHeaders();

        HttpEntity<String> httpEntity = new HttpEntity<>(reference, httpHeaders);
        ResponseEntity<StatusResponse> exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, StatusResponse.class);

        if (exchange.getBody() == null ) {
            throw new ApiResourceNotFoundException("Resource not found");
        }

        if (!"successful".equalsIgnoreCase(exchange.getBody().getMessage())) {
            throw new ApiBadRequestException(exchange.getBody().getMessage());
        }

        return exchange;
    }

    @Override
    public ResponseEntity<Response> cancel(RequestDto body) {
        URI uri = getURI("/payout/cancel");
        return getResponseResponseEntity(body, uri, "cancelled");
    }

    @Override
    public ResponseEntity<Response> update(RequestDto body) {
        URI uri = getURI("/payout/update");

        return getResponseResponseEntity(body, uri, "successful");
    }

    @Override
    public ResponseEntity<Response> create(RequestDto body) {
        URI uri = getURI("/payout/create");

        return getResponseResponseEntity(body, uri, "successful");
    }

    private ResponseEntity<Response> getResponseResponseEntity(RequestDto body, URI uri, String message) {
        HttpHeaders httpHeaders = getHeaders();
        HttpEntity<RequestDto> httpEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<Response> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Response.class);
        if (exchange.getBody() == null ) {
            throw new ApiResourceNotFoundException("Resource not found");
        }

        if (!message.equalsIgnoreCase(exchange.getBody().getMessage())) {
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
    private URI getURI(String url) {
        URI uri;
        try {
            uri = new URI(BASE_URL.concat(url));
        } catch (URISyntaxException exception) {
            throw new RuntimeException(exception);
        }
        return uri;
    }

}
