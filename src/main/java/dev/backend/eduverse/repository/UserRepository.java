package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<List<User>> findUserByEmailContainingIgnoreCase(String keyWord);

    @Query(value = "select id from User where email = :email", nativeQuery = true)
    Long searchIdByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<User> readUserByPagination(@Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "SELECT u FROM User u WHERE u.email = ?1")
    User processLogin(String email);
}
