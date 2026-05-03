package com.desafio.cep.client;

import com.desafio.cep.controllers.responses.CepResponse;
import com.desafio.cep.exceptions.ExternalCepApiException;
import com.desafio.cep.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class WireMockCepClient implements CepClient {

    private final RestTemplate restTemplate;

    @Value("${cep.client.base-url}")
    private String baseUrl;

    @Override
    public CepResponse findByCep(String cep) {
        try {
            String url = baseUrl + "/wiremock/" + cep;

            return restTemplate.getForObject(url, CepResponse.class);

        } catch (HttpClientErrorException.NotFound exception) {
            throw new NotFoundException("CEP não encontrado: " + cep);

        } catch (Exception exception) {
            throw new ExternalCepApiException("Erro ao consultar API externa de CEP.");
        }
    }
}
