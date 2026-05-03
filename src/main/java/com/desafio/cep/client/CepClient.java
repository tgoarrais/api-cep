package com.desafio.cep.client;

import com.desafio.cep.controllers.responses.CepResponse;

public interface CepClient {

    CepResponse findByCep(String cep);
}
