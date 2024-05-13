package dev.backend.eduverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NameAlreadyExistException extends RuntimeException {

  private String message;

  public NameAlreadyExistException(String message) {
    super(message);
  }
}
