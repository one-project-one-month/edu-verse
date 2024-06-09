package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.*;

public interface AuthService {
    ResponseAuthDto<UserDto> processUserLogin(AuthDto authDto);

    ResponseAuthDto<AdminDto> processAdminLogin(AuthDto authDto);

    boolean logout(String token);
}
