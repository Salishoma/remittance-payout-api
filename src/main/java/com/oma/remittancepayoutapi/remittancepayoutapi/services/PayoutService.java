package com.oma.remittancepayoutapi.remittancepayoutapi.services;

import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.RequestDto;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.Response;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.StatusResponse;
import org.springframework.http.ResponseEntity;

public interface PayoutService {
    ResponseEntity<Response> payout(RequestDto body);
    ResponseEntity<StatusResponse> status(String reference);

    ResponseEntity<Response> cancel(RequestDto body);

    ResponseEntity<Response> update(RequestDto body);

    ResponseEntity<Response> create(RequestDto body);
}
