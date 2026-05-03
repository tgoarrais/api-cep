package com.desafio.cep.mappers;

import com.desafio.cep.controllers.responses.CepResponse;
import com.desafio.cep.models.CepLogModel;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class CepLogMapper {

    public CepLogModel responseToModel(String normalizedCep, CepResponse cepResponse) {
        CepLogModel cepLogModel = new CepLogModel();
        cepLogModel.setCep(normalizedCep);
        cepLogModel.setLogradouro(cepResponse.logradouro());
        cepLogModel.setBairro(cepResponse.bairro());
        cepLogModel.setLocalidade(cepResponse.localidade());
        cepLogModel.setUf(cepResponse.uf());
        cepLogModel.setDataConsulta(LocalDateTime.now());

        return cepLogModel;
    }
}
