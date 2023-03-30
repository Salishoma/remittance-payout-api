package com.oma.remittancepayoutapi.remittancepayoutapi.auth;

//import com.oma.remittancepayoutapi.remittancepayoutapi.repository.AuthRepository;
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
import org.springframework.web.servlet.HandlerInterceptor;

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

    private AuthCredentials authCredentials;

//    private final AuthRepository authRepository;

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

            System.out.println("=======> Map: " + params);


            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);

            ResponseEntity<AuthCredentials> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, AuthCredentials.class);

            authCredentials = exchange.getBody();
//            AuthCredentials credentials = authRepository.findById("auth_token").orElse(null);

            if (authCredentials == null) {
                throw new ApiResourceNotFoundException("Request not found");
            }
            System.out.println("=====>authCredentials: " + authCredentials);
            authCredentials.setCreated_at(System.currentTimeMillis());
//            authRepository.save("auth_token", authCredentials);
//            HttpServletRequestUtil.addCookie(request, response, cookieName, exchange.getBody().getAccess_token());
            return exchange;


        } catch (URISyntaxException exception) {
            throw new RuntimeException(exception.getMessage());

        } catch (UnauthorizedException | HttpStatusCodeException exception) {
            throw new UnauthorizedException(exception.getMessage());
        }

    }

    public synchronized String getValidToken()
    {

//        AuthCredentials credentials1 = authRepository.findBbyToken("auth_token");
        AuthCredentials credentials = authenticate().getBody();
        System.out.println("=========> credentials: " + credentials);
        if (credentials == null) {
            throw new ApiBadRequestException("Invalid token");
        }
//        long duration = System.currentTimeMillis() - credentials.getCreated_at();
//
//        System.out.println("duration: " + duration);
//        System.out.println("authConfig.getExpTime(): " + credentials.getExpires_in());
//        if (duration >= credentials.getExpires_in()) {
//            throw new ApiBadRequestException("Token has expired");
//        }
        return credentials.getAccess_token();
    }

//    public boolean isValidToken(HttpServletRequest request, HttpServletResponse response, String cookieName) {
//        Map<String, String> cookieMap = HttpServletRequestUtil.getCookie(request, cookieName);
//        if (cookieMap.isEmpty() || authCredentials == null) {
//            System.out.println("========> is empty");
//            authCredentials = new AuthCredentials();
//            authCredentials.setCreated_at(System.currentTimeMillis());
//            authCredentials.setExpires_in(-1L);
//            HttpServletRequestUtil.addCookie(request, response, cookieName, "-1");
//            return true;
//        }
//        System.out.println("========> authCredentials req time: " + authCredentials.getCreated_at());
//        System.out.println("========> authCredentials exp time: " + authCredentials.getExpires_in());
//        String value = cookieMap.get(cookieName);
//
//        String[] arr = value.split("#");
//        String token = arr[0];
//        long validity = Long.parseLong(arr[1]);
//        long expTime = (System.currentTimeMillis() - validity);
//        System.out.println("========> expTime: " + expTime);
//        return token.equals(authCredentials.getAccess_token()) && expTime <= authCredentials.getExpires_in();
//
//    }
}
