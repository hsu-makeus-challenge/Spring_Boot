package umc.spring.service.UserService;

import umc.spring.domain.User;
import umc.spring.web.dto.UserDTO.UserRequestDTO;

public interface UserCommandService {
    void deleteUserWithRelatedEntities(Long userId);
    User joinMember(UserRequestDTO.JoinDto request);
}
