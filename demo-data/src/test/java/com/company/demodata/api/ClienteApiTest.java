package com.company.demodata.api;

import com.company.demodata.dto.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteApiTest {

    private static final String API_ROOT = "http://localhost";
    private static final String API_RESOURCE_ALL = "/v1/api/clientes";

    private static final String API_RESOURCE_DELETE = "/v1/api/clientes";
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
    void buscarClientes() {
        var clientes = webTestClient
                        .get()
                        .uri(API_RESOURCE_ALL)
                        .header("Authorization", "Basic " + "dXNlcjp1c2VyUGFzcw==")
                        .exchange()
                        .expectStatus()
                        .isFound();
                        //.expectBodyList(ClienteDto.class)
                        //.returnResult()
                        //.getResponseBody();
        //assertNotNull(clientes);
        //assert clientes.size() > 0;
    }

    @Test
    void eliminarCliente() {
        webTestClient
                .delete()
                .uri(API_RESOURCE_DELETE + "/1")
                .header("Authorization", "Basic " + "YWRtaW46YWRtaW5QYXNz")
                .exchange()
                .expectStatus()
                .isFound();
    }
}