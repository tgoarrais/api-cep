package com.desafio.cep.exceptions;

public class InvalidCepException extends RuntimeException {
    public InvalidCepException(String message) {
        super(message);
    }
}