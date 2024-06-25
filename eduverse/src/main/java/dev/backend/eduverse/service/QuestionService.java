/*
 * @Author : Alvin
 * @Date : 6/9/2024
 * @Time : 10:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    QuestionDto createQuestion(QuestionDto questionDto);

    List<QuestionDto> readQuestionByPagination(int pageNo, int limit) throws IllegalAccessException;

    QuestionDto updateQuestion(Long id, QuestionDto questionDto);

    void deleteQuestion(Long id);
}
