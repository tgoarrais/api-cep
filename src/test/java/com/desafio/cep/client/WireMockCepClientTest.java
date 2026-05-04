package com.desafio.cep.client;

import com.desafio.cep.controllers.responses.CepResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WireMockCepClientTest {

    private RestTemplate restTemplate;
    private WireMockCepClient wireMockCepClient;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        wireMockCepClient = new WireMockCepClient(restTemplate);

        ReflectionTestUtils.setField(
                wireMockCepClient,
                "baseUrl",
                "http://localhost:8089"
        );
    }

    @Test
    void shouldCallWireMockAndReturnCepResponse() {
        String cep = "01310930";
        String expectedUrl = "http://localhost:8089/wiremock/01310930";

        CepResponse cepResponse = new CepResponse(
                "01310-930",
                "Avenida Paulista",
                "Bela Vista",
                "São Paulo",
                "SP"
        );

        when(restTemplate.getForObject(expectedUrl, CepResponse.class))
                .thenReturn(cepResponse);

        CepResponse result = wireMockCepClient.findByCep(cep);

        assertNotNull(result);
        assertEquals("01310-930", result.cep());
        assertEquals("Avenida Paulista", result.logradouro());

        verify(restTemplate, times(1))
                .getForObject(expectedUrl, CepResponse.class);
    }
}