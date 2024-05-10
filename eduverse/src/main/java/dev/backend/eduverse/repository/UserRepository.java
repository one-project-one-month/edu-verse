package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<List<User>> findUserByEmailContainingIgnoreCase(String keyWord);

	@Query(value ="select id from User where email = :email", nativeQuery = true)
	Long searchIdByEmail(@Param("email") String email);
}
