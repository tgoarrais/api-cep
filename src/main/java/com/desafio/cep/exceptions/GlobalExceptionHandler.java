package com.desafio.cep.exceptions;

import com.desafio.cep.controllers.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCepException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidCepException(InvalidCepException exception) {
        return new ErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(ExternalCepApiException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorResponse handleNotFoundException(ExternalCepApiException exception) {
        return new ErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_GATEWAY.value(),
                LocalDateTime.now()
        );
    }

}
