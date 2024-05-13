/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Pathway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathwayRepository extends JpaRepository<Pathway, Long> {

  boolean existsByName(String name);
}
