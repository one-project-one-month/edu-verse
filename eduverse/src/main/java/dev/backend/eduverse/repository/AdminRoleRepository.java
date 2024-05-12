/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
*/
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRoleRepository extends JpaRepository<AdminRole, Long> {
}
