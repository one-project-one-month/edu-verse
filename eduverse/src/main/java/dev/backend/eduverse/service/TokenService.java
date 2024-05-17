package dev.backend.eduverse.service;

public interface TokenService {
    boolean existsByToken(String token);

    boolean existsByTokenAndIsAdmin(String token);
}
