package com.studiozero.projeto.web.handlers;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Object id) {
        super(entity + " not found for id: " + id);
    }
}

