package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.exception.InvalidTokenException;
import dev.backend.eduverse.model.Token;
import dev.backend.eduverse.repository.TokenRepository;
import dev.backend.eduverse.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    private final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public boolean existsByToken(String token) {
        Token tokenObj = checkTokenValidity(token);
        return tokenObj != null;
    }

    @Override
    public boolean existsByTokenAndIsAdmin(String token) {
        Token tokenObj = checkTokenValidity(token);
        return tokenObj != null && tokenObj.isAdmin();
    }

    private Token checkTokenValidity(String token) {
        logger.info("Checking Token Validity");
        Token tokenObj = tokenRepository
                .findByToken(
                        token.replaceFirst("Bearer ", "")//replace "Bearer " with ""
                );

        if (tokenObj == null) return null;

        //check if token is expired
        Date now = new Date();//get current
        long duration = now.getTime() - tokenObj.getCreatedAt().getTime();//calculate token duration

        //throw Exception if token is more than one day
        if (TimeUnit.MILLISECONDS.toDays(duration) > 1) {
            logger.info("Token is expired!");
            tokenRepository.delete(tokenObj);//delete token from database
            throw new InvalidTokenException("Your token is EXPIRED!");
        }
        
        logger.info("Token is valid");
        return tokenObj;
    }
}
