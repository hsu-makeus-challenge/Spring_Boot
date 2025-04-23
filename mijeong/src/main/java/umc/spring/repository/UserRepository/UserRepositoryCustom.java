package umc.spring.repository.UserRepository;

import umc.spring.web.dto.user.UserResponse;

public interface UserRepositoryCustom {
    UserResponse.MyPageDto getUserMyPage(Long userId);
}
