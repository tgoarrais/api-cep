package com.desafio.cep.mappers;

import com.desafio.cep.controllers.responses.CepResponse;
import com.desafio.cep.models.CepLogModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CepLogMapperTest {

    private final CepLogMapper cepLogMapper = new CepLogMapper();

    @Test
    void shouldMapCepResponseToCepLogModel() {
        String normalizedCep = "01310930";

        CepResponse cepResponse = new CepResponse(
                "01310-930",
                "Avenida Paulista",
                "Bela Vista",
                "São Paulo",
                "SP"
        );

        CepLogModel result = cepLogMapper.responseToModel(normalizedCep, cepResponse);

        assertNotNull(result);
        assertEquals("01310930", result.getCep());
        assertEquals("Avenida Paulista", result.getLogradouro());
        assertEquals("Bela Vista", result.getBairro());
        assertEquals("São Paulo", result.getLocalidade());
        assertEquals("SP", result.getUf());
        assertNotNull(result.getDataConsulta());
    }
}