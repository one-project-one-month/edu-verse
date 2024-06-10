/*
 * @Author : Alvin
 * @Date : 6/9/2024
 * @Time : 10:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.QuestionDto;
import dev.backend.eduverse.exception.ServiceException;
import dev.backend.eduverse.model.Exam;
import dev.backend.eduverse.model.Question;
import dev.backend.eduverse.repository.ExamRepository;
import dev.backend.eduverse.repository.QuestionRepository;
import dev.backend.eduverse.service.QuestionService;
import dev.backend.eduverse.util.response_template.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final ExamRepository examRepository;

    private final QuestionRepository questionRepository;

    private final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private final ModelMapper modelMapper;

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        logger.info("Entering the creation process");
        Question question = modelMapper.map(questionDto, Question.class);
        Exam exam = EntityUtil.getEntityById(examRepository, questionDto.getExamId(), "Question");
        question.setExam(exam);
        Question savedQuestion = EntityUtil.saveEntity(questionRepository, question, "question");
        return modelMapper.map(savedQuestion, QuestionDto.class);
    }

    @Override
    public List<QuestionDto> readQuestionByPagination(int pageNo, int limit) throws IllegalAccessException {
        logger.info("Entering the get all question process with pagination");
        pageNo = Math.max(pageNo, 1);
        limit = (limit < 1) ? 10 : limit;

        int offset = (pageNo - 1) * limit;
        try {
            List<Question> questionList = questionRepository.paginate(limit, offset);
            return questionList.stream()
                    .map(question -> modelMapper.map(question, QuestionDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Failed to retrieve questions with pagination", e);
            throw new ServiceException("An error occurred while retrieving questions", e);
        }
    }

    @Override
    public QuestionDto updateQuestion(Long id, QuestionDto questionDto) {
        logger.info("Updating question with ID: {}", id);
        Question existingQuestion = EntityUtil.getEntityById(questionRepository, id, "Question");
        modelMapper.map(questionDto, existingQuestion);
        EntityUtil.saveEntity(questionRepository, existingQuestion, "question");
        return modelMapper.map(existingQuestion, QuestionDto.class);
    }

    @Override
    public void deleteQuestion(Long id) {
        logger.info("Deleting question with ID: {}", id);
        EntityUtil.deleteEntity(questionRepository, id, "question");
    }

}
