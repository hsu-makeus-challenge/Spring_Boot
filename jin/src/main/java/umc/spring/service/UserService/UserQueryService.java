package umc.spring.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import umc.spring.domain.User;
import umc.spring.web.dto.user.UserResponseDTO;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> findUser(Long id);

    UserResponseDTO.UserInfoDTO getUserInfo(HttpServletRequest request);
}
