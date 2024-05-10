package dev.backend.eduverse.util.ResponeTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
  private HttpStatus header;
  private String message;
  private T data;
}
