package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.AnswerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnswerService {
    AnswerDto createAnswer(AnswerDto answerDto);

    List<AnswerDto> getAllAnswers();

    AnswerDto getAnswerById(Long id);

    AnswerDto updateAnswer(Long id, AnswerDto answerDto);

    void deleteAnswer(Long id);
}
