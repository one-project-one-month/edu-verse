package dev.backend.eduverse.util;

import dev.backend.eduverse.dto.ExamSubmissionDto;
import dev.backend.eduverse.model.Answer;
import dev.backend.eduverse.repository.AnswerRepository;
import dev.backend.eduverse.util.response_template.EntityUtil;

import java.util.List;

public class Helper {
    public static Long calculateTotalMark(List<ExamSubmissionDto> answers, AnswerRepository answerRepository) {
        long totalMark = 0L;
        for (ExamSubmissionDto submission : answers) {
            Answer correctAnswer = EntityUtil.getEntityById(answerRepository, submission.getId(), "Answer");
            if (correctAnswer != null && correctAnswer.isCorrect()) {
                totalMark += 1;
            }
        }
        return totalMark;
    }
}
