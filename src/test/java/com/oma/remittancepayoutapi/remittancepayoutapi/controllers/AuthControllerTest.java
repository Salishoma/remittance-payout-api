package com.oma.remittancepayoutapi.remittancepayoutapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.AuthCredentials;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations="classpath:config.properties")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private AuthCredentials credentials;

    @BeforeEach
    void setup () {
        objectMapper = new ObjectMapper();
        credentials = new AuthCredentials();
        credentials.setAccess_token("1a2b3c4d");
    }

    @Test
    @DisplayName("Authenticate Customer")
    public void authenticate() {
        try {
            mockMvc.perform(post("/auth")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(credentials)))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.expires_in", Matchers.is(1800)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}