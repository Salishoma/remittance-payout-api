package com.oma.remittancepayoutapi.remittancepayoutapi.controllers;

import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.RequestDto;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.Response;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.StatusResponse;
import com.oma.remittancepayoutapi.remittancepayoutapi.enums.Operation;
import com.oma.remittancepayoutapi.remittancepayoutapi.services.PayoutService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("payout")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request was successful"),
        @ApiResponse(responseCode = "400", description = "This is a bad request, please follow the API documentation for the proper request format."),
        @ApiResponse(responseCode = "401", description = "You do not have the credentials to access this resource."),
        @ApiResponse(responseCode = "404", description = "The resource is not available."),
        @ApiResponse(responseCode = "415", description = "The MediaType is unsupported."),
        @ApiResponse(responseCode = "500", description = "The server is down, please make sure that the Application is running")
})
public class PayoutController {
    private final PayoutService payoutService;

    @PostMapping
    public ResponseEntity<Response> payout (@RequestBody RequestDto body) {
        Optional<Operation> optional = Optional.ofNullable(body.getSource().getOperation());
        optional.orElseThrow(RuntimeException::new);

        return payoutService.payout(body);
    }

    @GetMapping(value = "status")
    public ResponseEntity<StatusResponse> status (@RequestParam("reference") String reference) {

        return payoutService.status(reference);
    }

    @PostMapping("cancel")
    public ResponseEntity<Response> cancel (@RequestBody RequestDto body) {

        return payoutService.cancel(body);
    }

    @PostMapping("update")
    public ResponseEntity<Response> update (@RequestBody RequestDto body) {
        return payoutService.update(body);
    }

    @PostMapping("create")
    public ResponseEntity<Response> create (@RequestBody RequestDto body) {

        return payoutService.create(body);
    }

}
