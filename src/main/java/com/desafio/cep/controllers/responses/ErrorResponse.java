package com.desafio.cep.controllers.responses;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        int status,
        LocalDateTime timestamp
) {
}