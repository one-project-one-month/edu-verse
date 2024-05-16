package dev.backend.eduverse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseAuthUserDto {
    private UserDTO loginUser;
    private String token;
}
