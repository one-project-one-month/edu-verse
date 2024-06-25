package dev.backend.eduverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoginFailException extends RuntimeException {
    public LoginFailException(String message) {
        super(message);
    }
}
