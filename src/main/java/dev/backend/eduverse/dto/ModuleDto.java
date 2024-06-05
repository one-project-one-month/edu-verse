package dev.backend.eduverse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {

  @NotNull(message = "Module name is required.")
  private String name;

  @NotBlank(message = "Module content can't be empty.")
  @Size(max = 1000, message = "Content's Length can't exceed 1000 characters.")
  private String content;

  private String duration;

  @NotNull(message = "Module must be related with Course.")
  private Long courseId;
}
