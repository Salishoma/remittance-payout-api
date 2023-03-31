package com.oma.remittancepayoutapi.remittancepayoutapi.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.github.javafaker.Faker;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.RequestDto;
import com.oma.remittancepayoutapi.remittancepayoutapi.sample_data.SampleData;
import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations="classpath:config.properties")
class PayoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private RequestDto requestBody;

    @Autowired
    PayoutController payoutController;


    private Faker faker;

    @BeforeEach
    void setup () {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        faker = new Faker(new Locale("en-NG"));
        try {
            requestBody = objectMapper.readValue(SampleData.jsonData, RequestDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Payout test")
    void payout() {
        try {
            requestBody.getTransaction().setReference(faker.bothify("########??", true));

            mockMvc.perform(post("/payout")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestBody)))
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.code", Matchers.is("00")))
                    .andExpect(jsonPath("$.message", Matchers.is("Successful")))
                    .andExpect(jsonPath("$.transaction.reference", Matchers.is(requestBody.getTransaction().getReference())));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    @DisplayName("Status of payout test")
    void status() {
        try {
            requestBody.getTransaction().setReference(faker.bothify("########??", true));

            String reference = "64377829";
            mockMvc.perform(get("/payout/status?reference=" + reference)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.code", Matchers.is("00")))
                    .andExpect(jsonPath("$.message", Matchers.is("Successful")))
                    .andExpect(jsonPath("$.transaction.reference", Matchers.is("64377829")));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void create() {
        try {
            requestBody.getTransaction().setReference(RandomStringUtils.randomAlphanumeric(8));
            mockMvc.perform(post("/payout/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestBody)))
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.code", Matchers.is("00")))
                    .andExpect(jsonPath("$.message", Matchers.is("Successful")))
                    .andExpect(jsonPath("$.transaction.reference", Matchers.is(requestBody.getTransaction().getReference())));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}