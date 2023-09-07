package com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
