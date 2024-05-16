package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.model.Token;
import dev.backend.eduverse.repository.TokenRepository;
import dev.backend.eduverse.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public boolean existsByToken(String token) {
        Token tokenObj = tokenRepository
                .findByToken(
                        token.replaceFirst("Bearer ", "")//replace "Bearer " with ""
                );
        return tokenObj != null;
    }
}
