/**
 * @Author : Kyaw Zaw Htet
 * @Date : 6/4/2024
 * @Time : 8:18 AM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.QuestionDto;
import dev.backend.eduverse.dto.UserExamDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserExamService {

    UserExamDto createUserExam(UserExamDto userExamDto);

    List<UserExamDto> readUserExamByPagination(int pageNo, int limit) throws IllegalAccessException;

    UserExamDto updateUserExam(UserExamDto userExamDto);

    void deleteUserExam(Long id);
}
