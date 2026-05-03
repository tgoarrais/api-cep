package com.desafio.cep.repositories;

import com.desafio.cep.models.CepLogModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CepLogRepository extends JpaRepository<CepLogModel, UUID> {
}
