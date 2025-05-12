package umc.spring.service.UserService;

import umc.spring.domain.User;
import umc.spring.web.dto.user.UserResponse;

public interface UserQueryService {
    UserResponse.MyPageDto getUserMyPage(Long userId);

    // User 존재 여부 검증
    Boolean existsUserById(Long userId);

    // 유저 반환
    User validateUser(Long userId);
}
