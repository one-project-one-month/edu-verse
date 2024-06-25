package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query(
            value = "SELECT * FROM admin WHERE user_name LIKE CONCAT('%', :search, '%') OR " +
                    "email LIKE CONCAT('%', :search, '%') ORDER BY id LIMIT :limit OFFSET :offset",
            nativeQuery = true
    )
    List<Admin> paginate(@Param("search") String search, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "SELECT * FROM admin WHERE email=:email", nativeQuery = true)
    Admin processLogin(@Param("email") String email);
}
