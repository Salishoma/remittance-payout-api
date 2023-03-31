package com.oma.remittancepayoutapi.remittancepayoutapi.services.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.CustomerValidateRequest;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.CustomerValidateResponse;
import com.oma.remittancepayoutapi.remittancepayoutapi.sample_data.SampleData;
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
class CustomerServiceImplTest {

    private CustomerValidateRequest customerValidateRequest;
    @Autowired
    private CustomerServiceImpl customerService;


    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        try {
            customerValidateRequest = objectMapper.readValue(SampleData.jsonData, CustomerValidateRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ReflectionTestUtils.setField(customerService, "BASE_URL", "https://staging.seerbitapigateway.com/fcmb/api/v1");
    }

    @Test
    void validateCustomer() {

        try {
            ResponseEntity<CustomerValidateResponse> responseEntity = customerService.validateCustomer(customerValidateRequest);
            assertEquals(200, responseEntity.getStatusCode().value());
            assertEquals("successful", Objects.requireNonNull(responseEntity.getBody()).getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}