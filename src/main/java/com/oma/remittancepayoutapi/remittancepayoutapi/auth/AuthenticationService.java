package com.oma.remittancepayoutapi.remittancepayoutapi.auth;

import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.AuthCredentials;
import com.oma.remittancepayoutapi.remittancepayoutapi.exceptions.ApiBadRequestException;
import com.oma.remittancepayoutapi.remittancepayoutapi.exceptions.ApiResourceNotFoundException;
import com.oma.remittancepayoutapi.remittancepayoutapi.exceptions.UnauthorizedException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    @Value("${seerbit.baseUrl}")
    private String BASE_URL;
    @Value("${seerbit.grantType}")
    private String GRANT_TYPE;

    @Value("${seerbit.clientId}")
    private String CLIENT_ID;

    @Value("${seerbit.clientSecret}")
    private String CLIENT_SECRET;
    private final RestTemplate restTemplate;

    public ResponseEntity<AuthCredentials> authenticate() {
        URI uri;
        try {
            uri = new URI(BASE_URL + "/auth");
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", GRANT_TYPE);
            params.add("client_id", CLIENT_ID);
            params.add("client_secret", CLIENT_SECRET);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);

            ResponseEntity<AuthCredentials> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, AuthCredentials.class);

            AuthCredentials authCredentials = exchange.getBody();

            if (authCredentials == null) {
                throw new ApiResourceNotFoundException("Request not found");
            }
            authCredentials.setCreated_at(System.currentTimeMillis());
            return exchange;


        } catch (URISyntaxException exception) {
            throw new RuntimeException(exception.getMessage());

        } catch (UnauthorizedException | HttpStatusCodeException exception) {
            throw new UnauthorizedException(exception.getMessage());
        }

    }

    public synchronized String getValidToken()
    {

        AuthCredentials credentials = authenticate().getBody();
        if (credentials == null) {
            throw new ApiBadRequestException("Invalid token");
        }

        return credentials.getAccess_token();
    }
}
