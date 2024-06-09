/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PathwayDto {

  @NotNull(message = "Name is required")
  @Size(min = 1, max = 20, message = "Name must be between 1 and 20 characters")
  private String name;

  @NotNull(message = "Long description is required")
  @Size(min = 1, max = 200, message = "Short description must be between 1 and 200 characters")
  private String description;
}
