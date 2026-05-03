package com.desafio.cep.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cep_logs")
public class CepLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 8)
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    @Column(length = 2)
    private String uf;

    @Column(name = "data_consulta", nullable = false)
    private LocalDateTime dataConsulta;


}
