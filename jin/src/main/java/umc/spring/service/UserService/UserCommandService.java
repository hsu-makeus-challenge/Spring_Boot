package umc.spring.service.UserService;

import umc.spring.domain.User;
import umc.spring.web.dto.user.UserRequestDTO;
import umc.spring.web.dto.user.UserResponseDTO;

public interface UserCommandService {

    User joinUser(UserRequestDTO.JoinDto request);

    UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO request);
}
