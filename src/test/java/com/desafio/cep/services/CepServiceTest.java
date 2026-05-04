package com.desafio.cep.services;

import com.desafio.cep.client.CepClient;
import com.desafio.cep.controllers.responses.CepResponse;
import com.desafio.cep.exceptions.InvalidCepException;
import com.desafio.cep.exceptions.NotFoundException;
import com.desafio.cep.mappers.CepLogMapper;
import com.desafio.cep.models.CepLogModel;
import com.desafio.cep.repositories.CepLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CepServiceTest {

    @Mock
    private CepClient cepClient;

    @Mock
    private CepLogRepository cepLogRepository;

    @Mock
    private CepLogMapper cepLogMapper;

    @InjectMocks
    private CepService cepService;

    private CepResponse cepResponse;
    private CepLogModel cepLogModel;

    @BeforeEach
    void setUp() {
        cepResponse = new CepResponse(
                "01310-930",
                "Avenida Paulista",
                "Bela Vista",
                "São Paulo",
                "SP"
        );

        cepLogModel = new CepLogModel();
        cepLogModel.setCep("01310930");
        cepLogModel.setLogradouro("Avenida Paulista");
        cepLogModel.setBairro("Bela Vista");
        cepLogModel.setLocalidade("São Paulo");
        cepLogModel.setUf("SP");
    }

    @Test
    void shouldFindCepAndSaveLogSuccessfully() {
        String cep = "01310930";

        when(cepClient.findByCep(cep)).thenReturn(cepResponse);
        when(cepLogMapper.responseToModel(cep, cepResponse)).thenReturn(cepLogModel);
        when(cepLogRepository.save(cepLogModel)).thenReturn(cepLogModel);

        CepResponse result = cepService.getCep(cep);

        assertNotNull(result);
        assertEquals("01310-930", result.cep());
        assertEquals("Avenida Paulista", result.logradouro());
        assertEquals("Bela Vista", result.bairro());
        assertEquals("São Paulo", result.localidade());
        assertEquals("SP", result.uf());

        verify(cepClient, times(1)).findByCep(cep);
        verify(cepLogMapper, times(1)).responseToModel(cep, cepResponse);
        verify(cepLogRepository, times(1)).save(cepLogModel);
    }

    @Test
    void shouldNormalizeCepBeforeCallingClient() {
        String cepWithMask = "01310-930";
        String normalizedCep = "01310930";

        when(cepClient.findByCep(normalizedCep)).thenReturn(cepResponse);
        when(cepLogMapper.responseToModel(normalizedCep, cepResponse)).thenReturn(cepLogModel);
        when(cepLogRepository.save(cepLogModel)).thenReturn(cepLogModel);

        CepResponse result = cepService.getCep(cepWithMask);

        assertNotNull(result);

        verify(cepClient, times(1)).findByCep(normalizedCep);
        verify(cepLogMapper, times(1)).responseToModel(normalizedCep, cepResponse);
        verify(cepLogRepository, times(1)).save(cepLogModel);
    }

    @Test
    void shouldThrowInvalidCepExceptionWhenCepHasLessThanEightDigits() {
        String invalidCep = "123";

        InvalidCepException exception = assertThrows(
                InvalidCepException.class,
                () -> cepService.getCep(invalidCep)
        );

        assertEquals("CEP deve conter 8 números.", exception.getMessage());

        verifyNoInteractions(cepClient);
        verifyNoInteractions(cepLogMapper);
        verifyNoInteractions(cepLogRepository);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenClientDoesNotFindCep() {
        String cep = "99999999";

        when(cepClient.findByCep(cep))
                .thenThrow(new NotFoundException("CEP não encontrado: " + cep));

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> cepService.getCep(cep)
        );

        assertEquals("CEP não encontrado: 99999999", exception.getMessage());

        verify(cepClient, times(1)).findByCep(cep);
        verifyNoInteractions(cepLogMapper);
        verifyNoInteractions(cepLogRepository);
    }
}