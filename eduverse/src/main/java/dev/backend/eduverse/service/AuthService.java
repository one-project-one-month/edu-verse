package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.AuthUserDto;
import dev.backend.eduverse.dto.ResponseAuthUserDto;
import dev.backend.eduverse.dto.UserDTO;

public interface AuthService {
    ResponseAuthUserDto processUserLogin(AuthUserDto authUserDto);
}
