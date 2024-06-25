/*
 * @Author : Alvin
 * @Date : 6/9/2024
 * @Time : 10:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Data Transfer Object for Answer.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AnswerDto {

    private Long id;

    /**
     * The name of the answer.
     * Must be between 1 and 100 characters long.
     */
    @NotNull(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    /**
     * Indicates if the answer is correct.
     * This field is required.
     */
    @NotNull(message = "Correct option is required")
    private Boolean correct;

    /**
     * The ID of the related question.
     */
    private Long questionId;
}
