package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.AdminDto;
import dev.backend.eduverse.dto.AuthDto;
import dev.backend.eduverse.dto.ResponseAuthDto;
import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.exception.LoginFailException;
import dev.backend.eduverse.model.Admin;
import dev.backend.eduverse.model.Token;
import dev.backend.eduverse.model.User;
import dev.backend.eduverse.repository.AdminRepository;
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
    private final AdminRepository adminRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, AdminRepository adminRepository, TokenRepository tokenRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.tokenRepository = tokenRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseAuthDto<UserDTO> processUserLogin(AuthDto authDto) {
        logger.info("Processing User Login");

        User userExists = userRepository.processLogin(authDto.getEmail());

        if (userExists == null || !BCrypt.checkpw(authDto.getPassword(), userExists.getPassword())) {
            //login failed
            logger.info("Login Failed");
            throw new LoginFailException("Incorrect email or password!");
        }

        //login success //user exists in table
        logger.info("Login Success");
        //generate token
        String token = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        //save token in Token table
        tokenRepository.save(new Token(token, false, userExists.getId()));

        UserDTO userDTO = modelMapper.map(userExists, UserDTO.class);
        userDTO.setPassword(null);

        logger.info("Successfully finish user Login process");
        return new ResponseAuthDto<>(userDTO, token);
    }

    @Override
    public ResponseAuthDto<AdminDto> processAdminLogin(AuthDto authDto) {
        logger.info("Processing Admin Login");

        Admin adminExists = adminRepository.processLogin(authDto.getEmail());

        if (adminExists == null || !BCrypt.checkpw(authDto.getPassword(), adminExists.getPassword())) {
            //login failed
            logger.info("Admin Login Failed");
            throw new LoginFailException("Incorrect email or password!");
        }

        //login success //admin exists in table
        logger.info("Admin Login Success");
        //generate token
        String token = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        //save token in Token table
        tokenRepository.save(new Token(token, true, adminExists.getId()));

        AdminDto adminDto = modelMapper.map(adminExists, AdminDto.class);
        adminDto.setPassword(null);

        logger.info("Successfully finish admin Login process");
        return new ResponseAuthDto<>(adminDto, token);
    }

    @Override
    public boolean logout(String token) {
        logger.info("Processing Logout");

        Token tokenObj = tokenRepository.findByToken(token.replaceFirst("Bearer ", ""));

        if (tokenObj == null) {
            logger.info("Logout Failed");
            return false;
        }

        logger.info("Logout Success");
        tokenRepository.delete(tokenObj);

        return true;
    }
}
