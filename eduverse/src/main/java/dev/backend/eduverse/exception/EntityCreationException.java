package dev.backend.eduverse.exception;

public class EntityCreationException extends RuntimeException {
    public EntityCreationException(String message) {
        super(message);
    }
}