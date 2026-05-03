package com.desafio.cep.exceptions;

public class ExternalCepApiException extends RuntimeException {
    public ExternalCepApiException(String message) {
        super(message);
    }
}
