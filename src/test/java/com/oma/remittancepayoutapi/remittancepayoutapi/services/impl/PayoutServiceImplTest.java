package com.oma.remittancepayoutapi.remittancepayoutapi.services.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.*;
import com.oma.remittancepayoutapi.remittancepayoutapi.sample_data.SampleData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestPropertySource(locations="classpath:config.properties")
class PayoutServiceImplTest {
    private RequestDto requestBody;
    @Autowired
    private PayoutServiceImpl payoutService;


    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        try {
            requestBody = objectMapper.readValue(SampleData.jsonData, RequestDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ReflectionTestUtils.setField(payoutService, "BASE_URL", "https://staging.seerbitapigateway.com/fcmb/api/v1");
    }


    @Test
    void payout() {

        try {
            requestBody.getTransaction().setReference(RandomStringUtils.randomAlphanumeric(8));
            ResponseEntity<Response> responseEntity = payoutService.payout(requestBody);
            Response response = responseEntity.getBody();
            assertEquals(200, responseEntity.getStatusCode().value());
            assertEquals("Successful", Objects.requireNonNull(response).getMessage());
            assertEquals("00", response.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void status() {
        try {
            requestBody.getTransaction().setReference(RandomStringUtils.randomAlphanumeric(8));
            ResponseEntity<StatusResponse> responseEntity = payoutService.status("2989972");
            StatusResponse response = responseEntity.getBody();
            assertEquals(200, responseEntity.getStatusCode().value());
            assertEquals("Successful", Objects.requireNonNull(response).getMessage());
            assertEquals("00", response.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void create() {
        try {
            requestBody.getTransaction().setReference(RandomStringUtils.randomAlphanumeric(8));
            ResponseEntity<Response> responseEntity = payoutService.create(requestBody);
            Response response = responseEntity.getBody();
            assertEquals(200, responseEntity.getStatusCode().value());
            assertEquals("Successful", Objects.requireNonNull(response).getMessage());
            assertEquals("00", response.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}