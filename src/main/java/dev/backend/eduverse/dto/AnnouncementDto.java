/**
 * @Author : Kyaw Zaw Htet @Date : 5/10/2024 @Time : 10:17 PM @Project_Name : eduverse
 */
package dev.backend.eduverse.dto;

import dev.backend.eduverse.util.NotificationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDto {

  private Long id;

  @NotEmpty(message = "Title should not be null or empty")
  @Size(max = 30, message = "Title is too long")
  private String title;

  @NotEmpty(message = "Content should not be null or empty")
  @Size(max = 100, message = "Content is too long")
  private String content;

  private LocalDate createdAt;

  private NotificationType notificationType;

  @NotNull(message = "AdminId should not be null or empty")
  private Long adminId;

  @NotNull(message = "CourseId should not be null or empty")
  private Long courseId;
}
