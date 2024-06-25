/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.dto;

import dev.backend.eduverse.util.CourseStatus;
import dev.backend.eduverse.util.Level;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
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
public class CourseDTO {

  @NotNull(message = "Name is required")
  @Size(min = 1, max = 20, message = "Name must be between 1 and 20 characters")
  private String name;

  @NotNull(message = "Level is required")
  private Level level;

  @NotNull(message = "Duration is required")
  private String duration;

  @NotNull(message = "Short description is required")
  @Size(min = 1, max = 100, message = "Short description must be between 1 and 100 characters")
  private String shortDescription;

  @NotNull(message = "Long description is required")
  @Size(min = 1, max = 500, message = "Short description must be between 1 and 500 characters")
  private String longDescription;

  // Auto fill createAt with current time stamp
  private LocalDate createdAt;
  private CourseStatus status;

  // I think we should auto fill Login user id
  // That is why I don't validate required in here
  private long adminId;
}
