/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.AdminRole;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRoleRepository extends JpaRepository<AdminRole, Long> {
	
	@Query(value = "SELECT * FROM admin_role ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<AdminRole> paginate(@Param("limit") int limit, @Param("offset") int offset);
}
