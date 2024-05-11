/**
 * @Author : Kyaw Zaw Htet
 * @Date : 5/11/2024
 * @Time : 10:44 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {

}
