package com.clodoaldo.api.cerveja.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CervejaAlreadyRegisteredException extends Exception{

    public CervejaAlreadyRegisteredException(String cervejaNome) {
        super(String.format("Beer with name %s already registered in the system.", cervejaNome));
    }
}
