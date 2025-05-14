package umc.spring.service.UserService;

import umc.spring.domain.User;
import umc.spring.web.dto.user.UserRequestDTO;

public interface UserCommandService {

    User joinUser(UserRequestDTO.JoinDto request);
}
