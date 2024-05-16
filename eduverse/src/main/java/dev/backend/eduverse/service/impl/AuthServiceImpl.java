package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.AuthUserDto;
import dev.backend.eduverse.dto.ResponseAuthUserDto;
import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.exception.LoginFailException;
import dev.backend.eduverse.model.Token;
import dev.backend.eduverse.model.User;
import dev.backend.eduverse.repository.TokenRepository;
import dev.backend.eduverse.repository.UserRepository;
import dev.backend.eduverse.service.AuthService;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final TokenRepository tokenRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseAuthUserDto processUserLogin(AuthUserDto authUserDto) {
        logger.info("Processing User Login");

        User userExists = userRepository.processLogin(authUserDto.getEmail());

        if (userExists == null || !BCrypt.checkpw(authUserDto.getPassword(), userExists.getPassword())) {
            //login failed
            logger.info("Login Failed");
            throw new LoginFailException("Incorrect email or password!");
        }

        //login success //user exists in table
        logger.info("Login Success");
        //generate token
        String token = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        //save token in Token table
        tokenRepository.save(new Token(token));

        UserDTO userDTO = modelMapper.map(userExists, UserDTO.class);
        userDTO.setPassword(null);

        logger.info("Successfully finish Login process");
        return new ResponseAuthUserDto(userDTO, token);
    }
}
