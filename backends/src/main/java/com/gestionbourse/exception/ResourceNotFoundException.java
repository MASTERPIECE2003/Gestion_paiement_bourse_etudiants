package com.gestionbourse.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s non trouvé avec %s: '%s'", resource, field, value));
    }
}