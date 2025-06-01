package umc.spring.service;

import umc.spring.domain.User;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

public interface UserCommandService {
    User joinUser(UserRequestDTO.JoinDto request);

    UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO request);
}
