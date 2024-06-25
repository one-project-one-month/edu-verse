/**
 * @Author : Kyaw Zaw Htet @Date : 5/11/2024 @Time : 10:41 PM @Project_Name : eduverse
 */
package dev.backend.eduverse.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDto {

  private Long id;

  @NotEmpty(message = "Title should not be null or empty")
  @Size(max = 20, message = "Title is too long")
  private String title;

  @NotEmpty(message = "Content should not be null or empty")
  @Size(max = 500, message = "Content is too long")
  private String content;

  @NotNull(message = "CourseId should not be null or empty")
  private Long courseId;

  @NotNull(message = "AdminId should not be null or empty")
  private Long adminId;
}
