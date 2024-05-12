/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminRoleDTO {

  @NotNull(message = "Code is required")
  @Pattern(regexp = "^\\w{4}$", message = "Code must be exactly 4 characters")
  private String code;

  @NotNull(message = "Role name is required")
  @Size(min = 1, max = 20, message = "Role name must be between 1 and 20 characters")
  private String roleName;
}
