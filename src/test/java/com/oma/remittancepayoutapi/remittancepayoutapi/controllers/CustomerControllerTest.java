package com.oma.remittancepayoutapi.remittancepayoutapi.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.CustomerValidateRequest;
import com.oma.remittancepayoutapi.remittancepayoutapi.enums.Operation;
import com.oma.remittancepayoutapi.remittancepayoutapi.sample_data.SampleData;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations="classpath:config.properties")
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private CustomerValidateRequest requestBody;

    @BeforeEach
    void setup () {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        try {
            requestBody = objectMapper.readValue(SampleData.jsonData, CustomerValidateRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Validate Customer")
    public void validateTest() {

        try {
            requestBody.getOrder().setAmount(BigDecimal.valueOf(40.00));
            requestBody.getSource().setOperation(Operation.account_enquiry);

            mockMvc.perform(post("/customer/validate")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestBody)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.code", Matchers.is("00")))
                    .andExpect(jsonPath("$.message", Matchers.is("Successful")));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}