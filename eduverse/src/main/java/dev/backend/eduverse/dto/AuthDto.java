package dev.backend.eduverse.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthDto {
    @NotNull(message = "Email is required.")
    private String email;

    @NotNull(message = "Password is required.")
    private String password;
}
