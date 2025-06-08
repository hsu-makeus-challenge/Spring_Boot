package umc.study.service.UserService;

import org.springframework.stereotype.Service;
import umc.study.domain.Users;
import umc.study.web.dto.UserRequestDTO;
import umc.study.web.dto.UserResponseDTO;

@Service
public interface UserCommandService {
    public Users joinUser(UserRequestDTO.JoinDto request);
    UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO request);

}
