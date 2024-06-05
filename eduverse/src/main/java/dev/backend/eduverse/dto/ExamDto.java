/**
 * @Author : Kyaw Zaw Htet
 * @Date : 6/4/2024
 * @Time : 7:50 AM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.dto;

import dev.backend.eduverse.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamDto {

    private Long id;
    private String examName;
    private LocalDate createdAt;
    private Long courseId;
    private Long adminId;
}
