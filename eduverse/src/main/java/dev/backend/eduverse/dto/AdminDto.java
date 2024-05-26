package dev.backend.eduverse.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class AdminDto {

    private Long id;

    @NotNull(message = "Username is required.")
    @Size(max = 20, message = "Username is too long")
    private String username;

    //    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    @NotNull(message = "Password is required.")
    @Size(min = 8, message = "Password must have at least 8 characters.")
    private String password;

    @NotNull(message = "Email is required.")
    @Email
    private String email;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "\\d{3} \\d{3} \\d{3,4}")
    private String phoneNumber;

    @NotNull(message = "Role Id is required")
    private Long roleId;
}
