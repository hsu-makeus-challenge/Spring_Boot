package umc.spring.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import umc.spring.web.dto.user.UserRequest;
import umc.spring.web.dto.user.UserResponse;

public interface UserCommandService {
    // 회원가입
    UserResponse.JoinResultDto joinUser(UserRequest.JoinDto requestDto);

    // 로그인
    UserResponse.LoginResultDto loginUser(UserRequest.LoginRequestDto requestDto, HttpServletResponse response);
}
