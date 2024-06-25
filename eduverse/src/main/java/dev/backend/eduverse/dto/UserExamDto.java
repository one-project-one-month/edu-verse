/**
 * @Author : Kyaw Zaw Htet
 * @Date : 6/4/2024
 * @Time : 8:19 AM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserExamDto {

    private Long id;
    private Long userId;
    private Long examId;
    private Long totalMark;
    private LocalDate createdAt;
}