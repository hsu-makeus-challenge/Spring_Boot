package umc.spring.service.UserService;

import umc.spring.web.dto.user.UserRequest;
import umc.spring.web.dto.user.UserResponse;

public interface UserCommandService {
    // 회원가입
    UserResponse.JoinResultDto joinUser(UserRequest.JoinDto requestDto);
}
