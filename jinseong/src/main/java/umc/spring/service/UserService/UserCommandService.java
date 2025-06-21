package umc.spring.service.UserService;

import umc.spring.domain.User;
import umc.spring.web.dto.UserDTO.UserRequestDTO;
import umc.spring.web.dto.UserDTO.UserResponseDTO;

public interface UserCommandService {
    void deleteUserWithRelatedEntities(Long userId);

    User joinMember(UserRequestDTO.JoinDto request);

    UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO request);

    UserResponseDTO.ReissueTokenDTO reissueAccessToken(String bearerToken);
}
