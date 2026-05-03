package com.desafio.cep.services;

import com.desafio.cep.client.CepClient;
import com.desafio.cep.controllers.responses.CepResponse;
import com.desafio.cep.exceptions.InvalidCepException;
import com.desafio.cep.mappers.CepLogMapper;
import com.desafio.cep.models.CepLogModel;
import com.desafio.cep.repositories.CepLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CepService {

    private final CepClient cepClient;
    private final CepLogRepository cepLogRepository;
    private final CepLogMapper cepLogMapper;

    public CepResponse getCep(String cep) {
        String normalizedCep = normalizedCep(cep);

        CepResponse cepResponse = cepClient.findByCep(normalizedCep);

        CepLogModel cepLog = cepLogMapper.responseToModel(normalizedCep, cepResponse);
        cepLogRepository.save(cepLog);

        return cepResponse;

    }

    private String normalizedCep(String cep) {

        if (cep == null || cep.isBlank()) {
            throw new InvalidCepException("Cep é Obrigatório");
        }

        String normalizedCep = cep.replaceAll("\\D", "");

        if (normalizedCep.length() != 8) {
            throw new InvalidCepException("CEP deve conter 8 números.");
        }

        return normalizedCep;

    }
}
