/*
 * @Author : Alvin
 * @Date : 6/9/2024
 * @Time : 10:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
