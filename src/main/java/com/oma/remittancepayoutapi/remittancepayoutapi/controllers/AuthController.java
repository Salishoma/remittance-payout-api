package com.oma.remittancepayoutapi.remittancepayoutapi.controllers;

import com.oma.remittancepayoutapi.remittancepayoutapi.auth.AuthenticationService;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.AuthCredentials;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request was successful"),
        @ApiResponse(responseCode = "400", description = "This is a bad request, please follow the API documentation for the proper request format."),
        @ApiResponse(responseCode = "401", description = "You do not have the credentials to access this resource."),
        @ApiResponse(responseCode = "404", description = "The resource is not available."),
        @ApiResponse(responseCode = "415", description = "The MediaType is unsupported."),
        @ApiResponse(responseCode = "500", description = "The server is down, please make sure that the Application is running")
})
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthCredentials> renewToken() {
        return authenticationService.authenticate();
    }
}
