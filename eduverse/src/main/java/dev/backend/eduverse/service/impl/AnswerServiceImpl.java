/*
 * @Author : Alvin
 * @Date : 6/9/2024
 * @Time : 10:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.AnswerDto;
import dev.backend.eduverse.exception.EntityNotFoundException;
import dev.backend.eduverse.model.Answer;
import dev.backend.eduverse.model.Question;
import dev.backend.eduverse.repository.AnswerRepository;
import dev.backend.eduverse.repository.QuestionRepository;
import dev.backend.eduverse.service.AnswerService;
import dev.backend.eduverse.util.response_template.DtoUtil;
import dev.backend.eduverse.util.response_template.EntityUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);

    @Override
    public AnswerDto createAnswer(AnswerDto answerDto) {
        logger.info("Entering the createAnswer process");
        Question question = EntityUtil.getEntityById(questionRepository, answerDto.getQuestionId(), "Question");
        Answer answer = DtoUtil.map(answerDto, Answer.class, modelMapper);
        answer.setQuestion(question);
        Answer savedAnswer = EntityUtil.saveEntity(answerRepository, answer, "Answer");
        logger.info("Answer created successfully with ID: {}", savedAnswer.getId());
        return DtoUtil.map(savedAnswer, AnswerDto.class, modelMapper);
    }

    @Override
    public List<AnswerDto> getAllAnswers() {
        logger.info("Entering the getAllAnswers process");
        List<Answer> answers = EntityUtil.getAllEntities(answerRepository);
        logger.info("Retrieved {} answers", answers.size());
        return DtoUtil.mapList(answers, AnswerDto.class, modelMapper);
    }

    @Override
    public AnswerDto getAnswerById(Long id) {
        try {
            Answer answer = EntityUtil.getEntityById(answerRepository, id, "Answer");
            return DtoUtil.map(answer, AnswerDto.class, modelMapper);
        } catch (EntityNotFoundException e) {
            logger.info("Answer with id {} not found", id);
            throw new EntityNotFoundException("Answer with id " + id + " not found");
        }
    }

    @Override
    public AnswerDto updateAnswer(Long id, AnswerDto answerDto) {
        logger.info("Updating answer with ID: {}", id);
        Answer existingAnswer = EntityUtil.getEntityById(answerRepository, id, "Answer");
        modelMapper.map(answerDto, existingAnswer);
        Answer updatedAnswer = EntityUtil.saveEntity(answerRepository, existingAnswer, "answer");
        logger.info("Answer updated successfully");
        return DtoUtil.map(updatedAnswer, AnswerDto.class, modelMapper);
    }


    @Override
    public void deleteAnswer(Long id) {
        logger.info("Deleting answer with ID: {}", id);
        EntityUtil.deleteEntity(answerRepository, id, "answer");
        logger.info("Answer deleted successfully");
    }
}