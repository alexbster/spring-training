package com.company.demodata.api;

import com.company.demodata.dto.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginApiTest {

    private static final String API_ROOT = "http://localhost";
    private static final String API_RESOURCE_ALL = "/v1/api/login";
    private WebTestClient webTestClient;

    @LocalServerPort
    private int port = 0;

    @BeforeEach
    public void setup() {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl(API_ROOT + ":" + port)
                .responseTimeout(Duration.ofSeconds(10))
                .build();
    }

    @Test
    void login() {
        webTestClient
                        .get()
                        .uri(API_RESOURCE_ALL)
                        .exchange()
                        .expectStatus()
                        .isOk();
    }
}