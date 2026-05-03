package com.desafio.cep.controllers.responses;

public record CepResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf
) {
}
