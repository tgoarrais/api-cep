package com.desafio.cep.controllers;


import com.desafio.cep.controllers.responses.CepResponse;
import com.desafio.cep.services.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ceps")
@RequiredArgsConstructor
public class CepController {

    private final CepService cepService;

    @GetMapping("/{cep}")
    @ResponseStatus(HttpStatus.OK)
    public CepResponse getCep(@PathVariable String cep) {
        return cepService.getCep(cep);
    }
}
