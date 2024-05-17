package dev.backend.eduverse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseAuthDto<T> {
    private T auth;
    private String token;
}
