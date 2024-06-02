package dev.backend.eduverse.dto;

import dev.backend.eduverse.model.Exam;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDto {
    private Long id, examId;
    private String question;

}
