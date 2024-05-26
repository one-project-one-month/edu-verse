package dev.backend.eduverse.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseDTO {
    private Long id;

    private LocalDate createdDate;

    @NotNull(message = "Course Id is required")
    private Long userId;

    @NotNull(message = "Course Id is required")
    private Long courseId;
}