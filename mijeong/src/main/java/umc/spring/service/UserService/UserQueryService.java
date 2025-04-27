package umc.spring.service.UserService;

import umc.spring.web.dto.user.UserResponse;

public interface UserQueryService {
    UserResponse.MyPageDto getUserMyPage(Long userId);
}
