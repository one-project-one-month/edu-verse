/**
 * @Author : Kyaw Zaw Htet
 * @Date : 6/4/2024
 * @Time : 8:22 AM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.AnnouncementDto;
import dev.backend.eduverse.dto.QuestionDto;
import dev.backend.eduverse.dto.UserExamDto;
import dev.backend.eduverse.exception.ResourceNotFoundException;
import dev.backend.eduverse.exception.ServiceException;
import dev.backend.eduverse.model.*;
import dev.backend.eduverse.repository.ExamRepository;
import dev.backend.eduverse.repository.UserExamRepository;
import dev.backend.eduverse.repository.UserRepository;
import dev.backend.eduverse.service.UserExamService;
import dev.backend.eduverse.util.response_template.EntityUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserExamServiceImpl implements UserExamService {

    private UserExamRepository userExamRepository;

    private UserRepository userRepository;

    private ExamRepository examRepository;

    private final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private final ModelMapper modelMapper;

    @Override
    public UserExamDto createUserExam(UserExamDto userExamDto) {
        logger.info("Entering the creation process");
        UserExam userExam = modelMapper.map(userExamDto, UserExam.class);
        User user = EntityUtil.getEntityById(userRepository, userExamDto.getUserId(), "user");
        Exam exam = EntityUtil.getEntityById(examRepository, userExamDto.getExamId(), "exam");
        userExam.setUser(user);
        userExam.setExam(exam);
        UserExam savedUserExam = EntityUtil.saveEntity(userExamRepository, userExam, "userExam");
        return modelMapper.map(savedUserExam, UserExamDto.class);
    }

    @Override
    public List<UserExamDto> readUserExamByPagination(int pageNo, int limit) throws IllegalAccessException {
        logger.info("Entering the get all userexam process with pagination");
        pageNo = Math.max(pageNo, 1);
        limit = (limit < 1) ? 10 : limit;

        int offset = (pageNo - 1) * limit;
        try {
            List<UserExam> userExamList = userExamRepository.paginate(limit, offset);
            return userExamList.stream()
                    .map(userExam -> modelMapper.map(userExam, UserExamDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Failed to retrieve userexams with pagination", e);
            throw new ServiceException("An error occurred while retrieving userexams", e);
        }
    }

    @Override
    public UserExamDto updateUserExam(UserExamDto userExamDto) {
        UserExam exitingUserExam =
                userExamRepository
                        .findById(userExamDto.getId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Announcement", "id", userExamDto.getId()));

        User exitingUser =
                userRepository
                        .findById(userExamDto.getUserId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("User", "id", userExamDto.getUserId()));
        Exam exitingExam =
                examRepository
                        .findById(userExamDto.getExamId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("User", "id", userExamDto.getUserId()));

        exitingUserExam.setCreatedAt(userExamDto.getCreatedAt());
        exitingUserExam.setTotalMark(userExamDto.getTotalMark());
        exitingUserExam.setUser(exitingUser);
        exitingUserExam.setExam(exitingExam);

        UserExam updatedUserExam = userExamRepository.save(exitingUserExam);

        return modelMapper.map(updatedUserExam, UserExamDto.class);
    }

    @Override
    public void deleteUserExam(Long id) {
        UserExam exitingUserExam =
                userExamRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("UserExam", "id", id));
        userExamRepository.deleteById(exitingUserExam.getId());
    }
}