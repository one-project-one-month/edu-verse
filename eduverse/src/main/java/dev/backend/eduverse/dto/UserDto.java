package dev.backend.eduverse.dto;

import dev.backend.eduverse.util.Gender;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  @NotNull(message = "Name is required")
  @Size(max = 20, message = "Name must be at most 20 characters long")
  private String name;

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
  @Size(max = 50, message = "Email must be at most 50 characters long")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(max = 50, message = "Password must be at most 50 characters long")
  @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
  private String password;

  @NotBlank(message = "Phone number is required")
  @Size(max = 12, message = "Phone number must be at most 12 characters long")
  @Pattern(regexp = "\\d{3} \\d{3} \\d{3,4}")
  private String phoneNumber;

  @Positive(message = "Age must be a positive number")
  @NotNull(message = "Age is required")
  private Integer age;

  @Past(message = "Date of birth must be in the past")
  @NotNull(message = "Date of birth is required")
  private LocalDate dob;

  @NotNull(message = "Gender is required")
  private Gender gender;

  @Size(max = 80, message = "Address must be at most 80 characters long")
  private String address;
}
