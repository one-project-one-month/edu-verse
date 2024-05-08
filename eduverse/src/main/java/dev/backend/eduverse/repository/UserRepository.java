package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<List<User>> findUserByEmailContainingIgnoreCase(String email);
}
